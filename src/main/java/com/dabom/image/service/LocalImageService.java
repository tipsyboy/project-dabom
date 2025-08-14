package com.dabom.image.service;

import com.dabom.image.model.dto.ImageUploadResponseDto;
import com.dabom.image.model.entity.Image;
import com.dabom.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dabom.image.util.ImageUtils.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LocalImageService implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.upload.url}")
    private String fileUrl;

    @Override
    public ImageUploadResponseDto uploadSingleImage(MultipartFile file, String directory) throws IOException {

        validateImage(file);

        String imageName = generateFileName(file.getOriginalFilename());
        String imagePath = uploadPath + "/" + directory + "/" + imageName;
        String imageUrlPath = fileUrl + "/" + directory + "/" + imageName;

        try {
            createDirectoryIfNotExists(uploadPath + "/" + directory);
            file.transferTo(new File(imagePath));

            Image entity = Image.builder()
                    .originalName(file.getOriginalFilename())
                    .imageName(imageName)
                    .imageUrl(imageUrlPath)
                    .imagePath(imagePath)
                    .fileSize(file.getSize())
                    .build();

            imageRepository.save(entity);

            return ImageUploadResponseDto.from(entity);

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드에 실패했습니다.", e);
        }
    }

    public List<ImageUploadResponseDto> uploadMultipleImages(List<MultipartFile> files, String directory) {
        return files.stream()
                .map(file -> {
                    try {
                        return uploadSingleImage(file, directory);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public String find(Integer idx) {
        Optional<Image> result = imageRepository.findById(idx);
        if (result.isPresent()) {
            return result.get().getImageUrl();
        }
        else throw new RuntimeException();
    }

    @Override
    public String deleteImage(Integer idx) {
        return "";
    }

}

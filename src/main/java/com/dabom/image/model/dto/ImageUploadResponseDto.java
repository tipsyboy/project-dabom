package com.dabom.image.model.dto;

import com.dabom.image.model.entity.Image;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageUploadResponseDto {
    private String originalName;
    private String imageName;
    private String imagePath;
    private String imageUrl;
    private Long imageSize;

    public static ImageUploadResponseDto from(Image entity) {

        return ImageUploadResponseDto.builder()
                .originalName(entity.getOriginalName())
                .imageName(entity.getImageName())
                .imagePath(entity.getImagePath())
                .imageUrl(entity.getImageUrl())
                .imageSize(entity.getFileSize())
                .build();
    }

}

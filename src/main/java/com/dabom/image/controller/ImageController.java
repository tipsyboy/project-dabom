package com.dabom.image.controller;

import com.dabom.common.BaseResponse;
import com.dabom.image.model.dto.ImageUploadResponseDto;
import com.dabom.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @GetMapping("/find/{imgidx}")
    public ResponseEntity<BaseResponse<String>> getImage(@RequestParam Integer imgidx) {
        String result = imageService.find(imgidx);

        return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
    }

    @GetMapping("/delete/{imgidx}")
    public ResponseEntity<Void> deleteImage(@RequestParam Integer imgidx) {
        imageService.deleteImage(imgidx);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponseDto> register(
            @RequestParam("file") MultipartFile file,
            @RequestParam("directory") String directory) throws IOException {

        ImageUploadResponseDto response = imageService.uploadSingleImage(file, directory);
        return ResponseEntity.ok(response);
    }


}

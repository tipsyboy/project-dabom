package com.dabom.image.controller;

import com.dabom.common.BaseResponse;
import com.dabom.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Value("${file.upload.path}")
    private String uploadPath;


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
}

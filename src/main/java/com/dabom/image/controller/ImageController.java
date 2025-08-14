package com.dabom.image.controller;

import com.dabom.common.BaseResponse;
import com.dabom.image.service.ImageService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Value("${file.upload.path}")
    private String uploadPath;


    @GetMapping("/find")
    public ResponseEntity<BaseResponse<String>> getImage(@RequestParam Integer idx)
    {
        String result = imageService.find(idx);

        return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
    }
}

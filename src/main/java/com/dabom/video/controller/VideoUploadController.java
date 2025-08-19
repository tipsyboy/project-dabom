package com.dabom.video.controller;

import com.dabom.video.service.VideoUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@Slf4j
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoUploadController {

    private final VideoUploadService videoUploadService;

    @PostMapping("/upload/{videoId}")
    public ResponseEntity<Integer> upload(@PathVariable Integer videoId, @RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(videoUploadService.upload(videoId, file));
    }
}
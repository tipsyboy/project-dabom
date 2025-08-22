package com.dabom.video.controller;

import com.dabom.common.BaseResponse;
import com.dabom.video.service.VideoEncodingService;
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
    private final VideoEncodingService videoEncodingService;

    @PostMapping("/upload")
    public ResponseEntity<Integer> upload(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(videoUploadService.upload(file));
    }

    // TODO: 인코딩 요청시 뭐 줄게 없는데 ??
    @PostMapping("/encode/{videoIdx}")
    public ResponseEntity<Void> encode(@PathVariable Integer videoIdx) throws IOException, InterruptedException {
        videoEncodingService.addEncodingJob(videoIdx);
        return ResponseEntity.ok(null);
    }
}
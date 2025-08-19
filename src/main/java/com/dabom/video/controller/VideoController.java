package com.dabom.video.controller;

import com.dabom.video.service.VideoSegmentService;
import com.dabom.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoServiceV4;
    private final VideoSegmentService videoSegmentService;

    @PostMapping("/upload")
    public ResponseEntity<Integer> upload(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(videoServiceV4.upload(file));
    }
}

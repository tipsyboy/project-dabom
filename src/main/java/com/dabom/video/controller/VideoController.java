package com.dabom.video.controller;


import com.dabom.video.model.dto.VideoMetadataRequestDto;
import com.dabom.video.service.VideoSegmentService;
import com.dabom.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/metadata")
    public ResponseEntity<Integer> uploadData(@RequestBody VideoMetadataRequestDto requestDto) throws IOException {
        return ResponseEntity.ok(videoService.createMetadata(requestDto));
    }
}

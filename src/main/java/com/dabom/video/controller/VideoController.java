package com.dabom.video.controller;


import com.dabom.common.BaseResponse;
import com.dabom.video.model.dto.VideoMetadataRequestDto;
import com.dabom.video.service.VideoEncodingService;
import com.dabom.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideoEncodingService videoEncodingService;

    @PatchMapping("/metadata/{videoIdx}")
    public ResponseEntity<BaseResponse<Integer>> uploadData(@PathVariable Integer videoIdx,
                                                            @RequestBody VideoMetadataRequestDto requestDto) throws IOException, InterruptedException {
        Integer i = videoService.mappingMetadata(requestDto);
//        videoEncodingService.addEncodingJob(videoIdx);
        videoEncodingService.encode(videoIdx);

        return ResponseEntity.ok(BaseResponse.of(i, HttpStatus.OK));
    }
}

package com.dabom.video.controller;

import com.dabom.video.service.VideoSegmentService;
import com.dabom.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController("/api/videos")
@RequiredArgsConstructor
public class VideoStreamController {

    private final VideoService videoServiceV4;
    private final VideoSegmentService videoSegmentService;

    @GetMapping("/{videoId}/stream")
    public ResponseEntity<Resource> stream(@PathVariable Integer videoId) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl")
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .body(videoServiceV4.stream(videoId));
    }

    @GetMapping("/{videoId}/{segmentName}")
    public ResponseEntity<?> getSegment(@PathVariable Integer videoId,
                                        @PathVariable String segmentName) throws IOException {

        log.debug("V4 세그먼트 요청 - videoId: {}, segmentName: {}", videoId, segmentName);

        Resource segmentResource = videoSegmentService.getSegmentResource(videoId, segmentName);
        // 실제 파일 크기 계산
        long fileSize = Files.size(segmentResource.getFile().toPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "video/mp2t") // MPEG-TS MIME 타입
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=31536000") // 1년 캐시
                .header("Access-Control-Allow-Origin", "*") // CORS
                .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize)) // 파일 크기
                .body(segmentResource);
    }
}

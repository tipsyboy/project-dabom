package com.dabom.video.service;

import com.dabom.video.model.Video;
import com.dabom.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoSegmentService {

    private final VideoRepository videoRepository;

    /**
     * 세그먼트 파일 리소스 조회
     */
    public Resource getSegmentResource(Integer videoId, String segmentName) throws IOException {
        log.debug("세그먼트 파일 요청 - videoId: {}, segmentName: {}", videoId, segmentName);

        // 2. 비디오 정보 조회
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다: " + videoId));

        // 3. 세그먼트 파일 경로 구성
        Path segmentPath = buildSegmentPath(video, segmentName);


        // 6. 파일 크기 확인 (로깅용)
        long fileSize = Files.size(segmentPath);
        log.debug("세그먼트 파일 제공 - videoId: {}, segmentName: {}, size: {} bytes",
                videoId, segmentName, fileSize);

        // 7. 리소스 생성 및 반환
        return new FileSystemResource(segmentPath);
    }


    /**
     * 세그먼트 파일 경로 구성
     */
    private Path buildSegmentPath(Video video, String segmentName) {
        Path videoDir = getVideoDirectory(video);
        return videoDir.resolve(segmentName);
    }

    /**
     * 비디오 디렉토리 경로 조회
     */
    private Path getVideoDirectory(Video video) {
        Path m3u8Path = Paths.get(video.getSavedPath());
        return m3u8Path.getParent();
    }
}


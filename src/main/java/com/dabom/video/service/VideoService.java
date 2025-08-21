package com.dabom.video.service;

import com.dabom.video.model.Video;
import com.dabom.video.model.dto.VideoMetadataRequestDto;
import com.dabom.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    @Transactional
    public Integer mappingMetadata(VideoMetadataRequestDto requestDto) {
        Video video = videoRepository.findById(requestDto.getIdx())
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다. idx=" + requestDto.getIdx()));

        video.mappingVideoMetadata(requestDto.getTitle(), requestDto.getDescription(), requestDto.isVisibility());

        // TODO: 리턴 뭐해야되지
        return 1;
    }

    public Resource stream(Integer videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("비디오 못찾음"));

        Path m3u8Path = Paths.get(video.getSavedPath());
        log.info("video path={}", video.getSavedPath());
        log.info("video path={}", Paths.get(video.getSavedPath()));

        if (!Files.exists(m3u8Path)) {
            throw new IllegalArgumentException("파일이 없음");
        }

        return new FileSystemResource(m3u8Path);
    }
}

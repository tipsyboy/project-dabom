package com.dabom.video.service;

import com.dabom.video.model.EncodingStatus;
import com.dabom.video.model.Video;
import com.dabom.video.model.dto.VideoMetadataRequestDto;
import com.dabom.video.repository.VideoRepository;
import com.dabom.video.utils.FfmpegEncoder;
import com.dabom.video.utils.VideoStatusManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final FfmpegEncoder ffmpegEncoder;
    private final VideoStatusManager videoStatusManager;


    public Integer createMetadata(VideoMetadataRequestDto requestDto) {
        Video video = requestDto.toEntity();
        return videoRepository.save(video).getIdx();
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

    private Integer saveVideoEntity(MultipartFile file, String originalFilename) {
        Video video = Video.builder()
                .originalFilename(originalFilename)
                .savedPath(null)
                .size(file.getSize())
                .contentType(file.getContentType())
                .encodingStatus(EncodingStatus.PROCESSING)
                .build();
        return videoRepository.save(video).getIdx();
    }

}

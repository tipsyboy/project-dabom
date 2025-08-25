package com.dabom.video.service;

import com.dabom.video.model.Video;
import com.dabom.video.model.dto.VideoInfoResponseDto;
import com.dabom.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class VideoStreamService {

    private final VideoRepository videoRepository;

    public VideoInfoResponseDto getVideoInfo(Integer videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("비디오 못찾음"));
        return VideoInfoResponseDto.toDto(video);
    }
}

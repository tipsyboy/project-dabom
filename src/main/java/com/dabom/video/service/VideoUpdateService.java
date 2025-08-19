package com.dabom.video.service;

import com.dabom.video.model.EncodingStatus;
import com.dabom.video.model.Video;
import com.dabom.video.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoUpdateService {

    private final VideoRepository videoRepository;

    @Transactional
    public void updateVideoRecord(Integer videoId, EncodingStatus status, String savedPath) {
        log.info("비디오 상태 업데이트 시작 - ID: {}, 상태: {}", videoId, status);

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("비디오를 찾을 수 없습니다: " + videoId));

        video.updateByEncodingFile(status);
        if (savedPath != null) {
            video.updateSavedPath(savedPath);
        }

        log.info("비디오 상태 업데이트 완료 - ID: {}, 상태: {}", videoId, status);
    }
}


package com.dabom.video.service;

import com.dabom.video.model.Video;
import com.dabom.video.repository.VideoRepository;
import com.dabom.video.utils.FfmpegEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@Service
public class VideoEncodingService {

    private final BlockingQueue<Integer> encodingQueue = new LinkedBlockingQueue<>();
    private final VideoRepository videoRepository;
    private final FfmpegEncoder ffmpegEncoder;
    private final AtomicBoolean processing = new AtomicBoolean(false);

    @Async("ffmpegExecutor")
    public void encode(Integer videoIdx) throws IOException {
        Video video = videoRepository.findById(videoIdx)
                .orElseThrow();

        String savedPath = ffmpegEncoder.encode(video.getOriginalPath());
        video.updateSavedPath(savedPath);
        log.info("saved path={}", savedPath);
        videoRepository.save(video); // TODO: Manager
    }
}
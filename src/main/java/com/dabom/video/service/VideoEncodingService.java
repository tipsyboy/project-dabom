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

@Slf4j
@RequiredArgsConstructor
@Service
public class VideoEncodingService {

    private final BlockingQueue<Integer> encodingQueue = new LinkedBlockingQueue<>();

    private final VideoRepository videoRepository;
    private final FfmpegEncoder ffmpegEncoder;

    public void addEncodingJob(Integer videoIdx) throws InterruptedException, IOException {
        encodingQueue.put(videoIdx);
        log.info("video {} enqueue", videoIdx);
        processQueueAsync(); // 작업 호출
    }

    @Async("ffmpegExecutor")
    public void processQueueAsync() throws IOException {
        Integer videoIdx = encodingQueue.poll();
        if (videoIdx == null) {
            return;
        }

        Video video = videoRepository.findById(videoIdx)
                .orElseThrow(() -> new IllegalArgumentException("비디오가 없습니다. idx=" + videoIdx));


        log.info("Video {} 인코딩 시작 (스레드: {})", videoIdx, Thread.currentThread().getName());
        ffmpegEncoder.encode(video.getOriginalPath());
        log.info("Video {} 인코딩 완료 (스레드: {})", videoIdx, Thread.currentThread().getName());
    }
}

package com.dabom.video.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class FfmpegService {

    public static final String SEGMENT_PATTERN = "segment_%03d.ts";
    public static final String INDEX_FILE_NAME = "index.m3u8";
    @Value("${file.ffmpeg.path}")
    private String ffmpegPath;

    @Value("${file.ffmpeg.hls.segment-duration}")
    private int hlsSegmentDuration;

    public CompletableFuture<Path> encodeToHlsAsync(Path inputFile, Path outputDir) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("백그라운드에서 인코딩 시작: {}", Thread.currentThread().getName());

                Path m3u8Path = outputDir.resolve(INDEX_FILE_NAME);
                Path segmentPattern = getSegmentPattern(outputDir);

                Process process = buildProcessingCommand(inputFile, segmentPattern, m3u8Path);

                // 이 부분이 별도 스레드에서 실행됨!
                StringBuilder output = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                        log.debug("FFmpeg[{}]: {}", Thread.currentThread().getName(), line);
                    }
                }

                int exitCode = process.waitFor(); // 여기서 블로킹되지만 별도 스레드이므로 OK
                if (exitCode != 0) {
                    throw new RuntimeException("FFmpeg 실패: " + output.toString());
                }

                log.info("인코딩 완료: {} (스레드: {})", m3u8Path, Thread.currentThread().getName());
                return m3u8Path;

            } catch (Exception e) {
                log.error("인코딩 실패", e);
                throw new RuntimeException("인코딩 실패", e);
            }
        }, getAsyncExecutor()); // 커스텀 스레드풀 사용
    }

    private Process buildProcessingCommand(Path inputFile, Path segmentPattern, Path m3u8Path) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath,
                "-i", inputFile.toString(),
                "-c", "copy",
                "-hls_time", String.valueOf(hlsSegmentDuration),
                "-hls_list_size", "0",
                "-f", "hls",
                "-hls_segment_filename", segmentPattern.toString(),
                "-y",
                m3u8Path.toString()
        );

        pb.redirectErrorStream(true);
        return pb.start();
    }

    // 인코딩 전용 스레드풀 설정
    private Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);    // 동시 인코딩 2개
        executor.setMaxPoolSize(4);     // 최대 4개
        executor.setQueueCapacity(10);  // 대기열 10개
        executor.setThreadNamePrefix("encoding-");
        executor.initialize();
        return executor;
    }

    private Path getSegmentPattern(Path outputDir) {
        Path normalizedDir = outputDir.normalize().toAbsolutePath();
        return normalizedDir.resolve(SEGMENT_PATTERN);
    }
}


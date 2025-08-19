package com.dabom.video.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FfmpegEncoder {

    public static final String SEGMENT_PATTERN = "segment_%03d.ts";
    public static final String INDEX_FILE_NAME = "index.m3u8";

    @Value("${file.ffmpeg.path}")
    private String ffmpegPath;

    @Value("${file.ffmpeg.hls.segment-duration}")
    private int hlsSegmentDuration;

    @Async("ffmpegExecutor") // 사용할 스레드풀 지정
    public CompletableFuture<Path> encodeToHlsAsync(Path inputFile, Path outputDir) {
        try {
            log.info("백그라운드에서 인코딩 시작: {}", Thread.currentThread().getName());

            Path m3u8Path = outputDir.resolve(INDEX_FILE_NAME);
            Path segmentPattern = getSegmentPattern(outputDir);

            Process process = buildProcessingCommand(inputFile, segmentPattern, m3u8Path);

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    log.debug("FFmpeg[{}]: {}", Thread.currentThread().getName(), line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("FFmpeg 실패: " + output.toString());
            }

            log.info("인코딩 완료: {} (스레드: {})", m3u8Path, Thread.currentThread().getName());
            return CompletableFuture.completedFuture(m3u8Path);

        } catch (Exception e) {
            log.error("인코딩 실패", e);
            throw new RuntimeException("인코딩 실패", e);
        }
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
    private Path getSegmentPattern(Path outputDir) {
        Path normalizedDir = outputDir.normalize().toAbsolutePath();
        return normalizedDir.resolve(SEGMENT_PATTERN);
    }
}

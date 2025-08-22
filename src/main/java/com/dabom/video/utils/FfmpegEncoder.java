package com.dabom.video.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FfmpegEncoder {

    private static final String VIDEO_UPLOAD_DIR = "videos/";
    private static final String INDEX_FILE_NAME = "index.m3u8";
    private static final String SEGMENT_PATTERN = "segment_%08d.ts";

    @Value("${file.ffmpeg.path}")
    private String ffmpegPath;

    @Value("${file.ffmpeg.hls.segment-duration}")
    private int hlsSegmentDuration;

    public void encode(String originalPath) throws IOException {
        log.debug(">>>>>> start ffmpeg encoder >>>>>>");

        try {
            Path encodingDir = makeEncodingDir();
            Process process = buildProcessingCommand(originalPath, encodingDir);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                log.error("Video 인코딩 실패 (exitCode={})", exitCode);
            }
        } catch (Exception e) {
            log.error("Video 인코딩 중 예외 발생", e);
        }
    }

    // ===== ===== //
    private Process buildProcessingCommand(String originalPath, Path encodingDir) throws IOException {
        Path segmentPattern = encodingDir.resolve(SEGMENT_PATTERN);
        Path indexFile = encodingDir.resolve(INDEX_FILE_NAME);

        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath,
                "-i", originalPath,
                "-c", "copy",
                "-hls_time", String.valueOf(hlsSegmentDuration),
                "-hls_list_size", "0",
                "-f", "hls",
                "-hls_segment_filename", segmentPattern.toString(),
                "-y",
                indexFile.toString()
        );

        pb.redirectErrorStream(true);
        return pb.start();
    }

    private Path makeEncodingDir() throws IOException {
        UUID uuid = UUID.randomUUID();

        Path encodingDir = Paths.get(
                VIDEO_UPLOAD_DIR,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd")),
                uuid.toString()
        );
        Files.createDirectories(encodingDir);
        return encodingDir;
    }
}

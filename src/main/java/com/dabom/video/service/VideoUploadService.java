package com.dabom.video.service;


import com.dabom.video.model.Video;
import com.dabom.video.model.VideoStatus;
import com.dabom.video.repository.VideoRepository;
import com.dabom.video.utils.FfmpegEncoder;
import com.dabom.video.utils.VideoStatusManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class VideoUploadService {

    private static final String VIDEO_UPLOAD_DIR = "videos/";
    private static final String VIDEO_TEMP_DIR = "temp/";

    private final VideoRepository videoRepository;

    public Integer upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }

        // 1. 파일 임시 저장.
        String uuid = UUID.randomUUID().toString();
        String savedTempPath = saveTempFile(file, originalFilename, uuid);

        Video video = Video.builder()
                .originalFilename(originalFilename)
                .originalPath(savedTempPath)
                .originalSize(file.getSize())
                .status(VideoStatus.UPLOADING)
                .contentType(file.getContentType())
                .build();

        Video savedVideo = videoRepository.save(video);
        savedVideo.updateVideoStatus(VideoStatus.UPLOADED);
        return savedVideo.getIdx();

//        // 2. ffmpeg service에 HLS 인코딩 요청
//        String hlsOutputDir = createHlsDir(uuid);
//
//        // 3. 비동기 인코딩 시작 (백그라운드)
////        ffmpegService.encodeToHls(Paths.get(savedTempPath), Paths.get(hlsOutputDir));
//        ffmpegEncoder.encodeToHlsAsync(Paths.get(savedTempPath), Paths.get(hlsOutputDir))
//                .thenAccept(m3u8Path -> {
//                    // 성공 시 (별도 스레드에서 실행)
//                    log.info("인코딩 완료: {}", m3u8Path);
//                    videoStatusManager.updateVideoRecord(videoId, EncodingStatus.COMPLETED, m3u8Path.toString());
//                    sendNotification(videoId, "인코딩이 완료되었습니다.");
//                })
//                .exceptionally(throwable -> {
//                    // 실패 시 (별도 스레드에서 실행)
//                    log.error("인코딩 실패", throwable);
//                    videoStatusManager.updateVideoRecord(videoId, EncodingStatus.FAILED, null);
//                    sendNotification(videoId, "인코딩이 실패했습니다.");
//                    return null;
//                });

    }

    // ===== ===== //
    private String saveTempFile(MultipartFile file, String originalFilename, String uuid) throws IOException {
        String todayPath = createTodayUploadPath();
        String ext = extractExtension(originalFilename);

        String savedFilename = uuid + "." + ext;
        String savedPath = Paths.get(todayPath, savedFilename).toAbsolutePath().toString();
        file.transferTo(new File(savedPath));

        return savedPath;
    }

    private String createTodayUploadPath() throws IOException {
        String todayDir = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Path uploadDir = Paths.get(VIDEO_TEMP_DIR, todayDir);
        Files.createDirectories(uploadDir);

        return uploadDir.toString();
    }

    private String extractExtension(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

    private String createHlsDir(String uuid) throws IOException {
        String outputPath = VIDEO_UPLOAD_DIR +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "/"
                + uuid;
        Path hlsOutputPath = Paths.get(outputPath);
        Files.createDirectories(hlsOutputPath);
        return hlsOutputPath.toAbsolutePath().toString();
    }

    private void sendNotification(Integer id, String message) {
        // 알림 발송 로직
        log.info("알림 발송 - ID: {}, 메시지: {}", id, message);
    }
}

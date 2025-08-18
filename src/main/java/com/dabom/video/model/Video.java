package com.dabom.video.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title; // 영상 제목

    private String originalFilename;  // 업로드한 원본 파일 이름 (예: user_uploaded.mp4)
    private String savedPath; // 실제 저장된 경로 (로컬 경로 or S3 URL or m3u8 경로)

    private String contentType; // MIME 타입 (video/mp4, application/x-mpegURL 등)
    private Long size; // 파일 크기 (bytes)


    @Enumerated(EnumType.STRING)
    private StorageType storageType; // 저장소 유형 (LOCAL, S3)

    @Enumerated(EnumType.STRING)
    private ServingType servingType; // 서빙 방식 (RANGE, HLS)

    @Enumerated(EnumType.STRING)
    private EncodingStatus encodingStatus; // 인코딩 상태

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public Video(String title, String originalFilename, String savedPath,
                 String contentType, Long size,
                 StorageType storageType, ServingType servingType, EncodingStatus encodingStatus) {
        this.title = title;
        this.originalFilename = originalFilename;
        this.savedPath = savedPath;
        this.contentType = contentType;
        this.size = size;
        this.storageType = storageType;
        this.servingType = servingType;
        this.encodingStatus = encodingStatus;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateByEncodingFile(EncodingStatus status) {
        this.encodingStatus = status;
    }

    public void updateSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }
}


package com.dabom.video.model;

import com.dabom.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String title;
    private String description;
    private boolean isVisibility;

    private String originalFilename;  // 업로드한 원본 파일 이름 (예: user_uploaded.mp4)
    private String savedPath; // 실제 저장된 경로 (로컬 경로 or S3 URL or m3u8 경로)

    private String contentType; // MIME 타입 (video/mp4, application/x-mpegURL 등)
    private Long size; // 파일 크기 (bytes)

    @Enumerated(EnumType.STRING)
    private EncodingStatus encodingStatus; // 인코딩 상태


    @Builder
    public Video(String title, String description, boolean isVisibility,
                 String originalFilename, String savedPath,
                 String contentType, Long size, EncodingStatus encodingStatus) {
        this.title = title;
        this.description = description;
        this.originalFilename = originalFilename;
        this.savedPath = savedPath;
        this.contentType = contentType;
        this.size = size;
        this.encodingStatus = encodingStatus;
    }

    public void updateByEncodingFile(EncodingStatus status) {
        this.encodingStatus = status;
    }

    public void updateSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }
}

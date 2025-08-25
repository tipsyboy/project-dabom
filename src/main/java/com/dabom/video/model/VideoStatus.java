package com.dabom.video.model;

public enum VideoStatus {

    UPLOADING(1, "원본 파일 업로드 중"),
    UPLOADED(2, "원본 파일 업로드 완료"),

    ENCODING_PENDING(3, "인코딩 대기중"),

    ENCODING(4, "인코딩 중"),
    ENCODED(5, "인코딩 완료"),

    DONE(6, "처리 완료"),
    FAILED(7, "처리 실패");

    private final int code;
    private final String description;

    VideoStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}

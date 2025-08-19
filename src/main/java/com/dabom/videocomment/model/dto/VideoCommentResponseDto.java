package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class VideoCommentResponseDto {
    private Integer idx;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Boolean isModified;

    public static VideoCommentResponseDto from(VideoComment entity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return VideoCommentResponseDto.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt().format(formatter))
                .updatedAt(entity.getUpdatedAt().format(formatter))
                .isModified(!entity.getCreatedAt().equals(entity.getUpdatedAt()))
                .build();

    }
}

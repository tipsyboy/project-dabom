package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoCommentUpdateDto {
    private Integer idx;
    private String content;

    public VideoComment toEntity(VideoComment entity) {
        entity.updateContent(this.content); // 기존 엔티티 수정
        return entity;
    }
}

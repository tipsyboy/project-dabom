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
        return VideoComment.builder()
                .idx(entity.getIdx())
                .content(this.content)
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}

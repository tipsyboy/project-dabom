package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Getter;

@Getter
public class VideoCommentDeleteDto {
    private Integer idx;


    public VideoComment softDelete(VideoComment entity) {
        VideoComment dto = VideoComment.builder()
                .content(entity.getContent())
                .isDeleted(true)
                .build();
        return dto;
    }
}
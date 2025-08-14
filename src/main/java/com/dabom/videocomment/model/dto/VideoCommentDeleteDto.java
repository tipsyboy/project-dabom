package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;

import java.util.Optional;

@Getter
public class VideoCommentDeleteDto {
    private Integer idx;


    public VideoComment softDelete(VideoComment entity) {
        VideoComment dto = VideoComment.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .isDeleted(true)
                .build();
        return dto;
    }
}
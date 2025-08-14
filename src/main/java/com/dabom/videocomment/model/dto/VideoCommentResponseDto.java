package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoCommentResponseDto {
    private Integer idx;
    private String content;

    public static VideoCommentResponseDto from(VideoComment entity){
        VideoCommentResponseDto dto = VideoCommentResponseDto.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .build();

        return dto;
    }
}

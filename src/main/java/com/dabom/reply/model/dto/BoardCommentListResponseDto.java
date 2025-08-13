package com.dabom.reply.model.dto;

import com.dabom.reply.model.entity.BoardComment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardCommentListResponseDto {
    private Integer idx;
    private String content;

    public static BoardCommentListResponseDto from(BoardComment entity) {
        return BoardCommentListResponseDto.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .build();
    }
}

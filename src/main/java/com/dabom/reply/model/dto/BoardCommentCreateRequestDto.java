package com.dabom.reply.model.dto;

import com.dabom.reply.model.entity.BoardComment;
import lombok.Getter;

@Getter
public class BoardCommentCreateRequestDto {
    private String content;

    public BoardComment toEntity() {
        return BoardComment.builder()
                .content(content)
                .build();
    }
}

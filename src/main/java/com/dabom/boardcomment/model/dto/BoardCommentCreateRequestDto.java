package com.dabom.boardcomment.model.dto;

import com.dabom.boardcomment.model.entity.BoardComment;
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

package com.dabom.boardcomment.model.dto;

import com.dabom.boardcomment.model.entity.BoardComment;
import com.dabom.channelboard.model.entity.ChannelBoard;
import lombok.Getter;

@Getter
public class BoardCommentCreateRequestDto {
    private String content;


    public BoardComment toEntity(ChannelBoard board) {
        return BoardComment.builder()
                .content(content)
                .board(board)
                .build();
    }
}
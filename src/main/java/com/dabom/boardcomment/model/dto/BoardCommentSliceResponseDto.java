package com.dabom.boardcomment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentSliceResponseDto {
    private List<BoardCommentResponseDto> content;
    private boolean hasNext;
    private long totalCount;
}


package com.dabom.channelboard.model.dto;


import com.dabom.channelboard.model.entity.ChannelBoard;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChannelBoardReadResponseDto {
    private Integer idx;
    private String title;
    private String contents;
    private String createAt;
    private Integer commentCount;

    public static ChannelBoardReadResponseDto from(ChannelBoard entity) {
        return ChannelBoardReadResponseDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .createAt(entity.getCreateAt())
                .commentCount(entity.getBoardCommentList().size())
                .build();
    }
}

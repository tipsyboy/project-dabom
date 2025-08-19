package com.dabom.channelboard.model.dto;

import com.dabom.channelboard.model.entity.ChannelBoard;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ChannelBoardReadResponseDto {
    private Integer idx;
    private String title;
    private String contents;
    private String createdAt;
    private Integer commentCount;


    public static ChannelBoardReadResponseDto from(ChannelBoard entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return ChannelBoardReadResponseDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .createdAt(entity.getCreatedAt().format(formatter))
                .commentCount(0) // 기본값
                .build();
    }


    public static ChannelBoardReadResponseDto fromWithCommentCount(ChannelBoard entity, Long commentCount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return ChannelBoardReadResponseDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .createdAt(entity.getCreatedAt().format(formatter))
                .commentCount(commentCount.intValue())
                .build();
    }
}
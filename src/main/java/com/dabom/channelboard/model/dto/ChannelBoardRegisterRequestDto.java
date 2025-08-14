package com.dabom.channelboard.model.dto;


import com.dabom.channelboard.model.entity.ChannelBoard;

public record ChannelBoardRegisterRequestDto(String title, String contents, String createAt) {

    public ChannelBoard toEntity() {
        ChannelBoard entity = ChannelBoard.builder()
                .title(title)
                .contents(contents)
                .createAt(createAt)
                .build();
        return entity;
    }
}

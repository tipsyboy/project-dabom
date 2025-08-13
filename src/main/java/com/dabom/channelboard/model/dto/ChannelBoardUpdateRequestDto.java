package com.dabom.channelboard.model.dto;


import com.dabom.channelboard.model.entity.ChannelBoard;

public class ChannelBoardUpdateRequestDto {

    private Integer idx;
    private String title;
    private String contents;
    private String createAt;
    private Boolean isDeleted;

    public ChannelBoard toEntity() {
        ChannelBoard entity = ChannelBoard.builder()
                .idx(idx)
                .title(title)
                .contents(contents)
                .createAt(createAt)
                .build();
        return entity;
    }

    public ChannelBoard softDelete(ChannelBoard entity) {
        entity.setIsDeleted(true);
        return entity;
    }

}


package com.dabom.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberChannelNameCheckRequestDto {
    @NotBlank
    private String channelName;
}

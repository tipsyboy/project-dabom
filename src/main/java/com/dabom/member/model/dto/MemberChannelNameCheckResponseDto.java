package com.dabom.member.model.dto;

public record MemberChannelNameCheckResponseDto(Boolean isDuplicate) {
    public static MemberChannelNameCheckResponseDto of(Boolean isDuplicate) {
        return new MemberChannelNameCheckResponseDto(isDuplicate);
    }
}

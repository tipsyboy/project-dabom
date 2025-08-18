package com.dabom.member.model.dto;

public record MemberEmailCheckResponseDto(Boolean isDuplicate) {
    public static MemberEmailCheckResponseDto of(Boolean isDuplicate) {
        return new MemberEmailCheckResponseDto(isDuplicate);
    }
}

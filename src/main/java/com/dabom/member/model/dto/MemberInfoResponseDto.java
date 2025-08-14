package com.dabom.member.model.dto;

import com.dabom.member.model.entity.Member;

public record MemberInfoResponseDto(String name, String email, String userRole) {
    public static MemberInfoResponseDto toDto(Member member) {
        return new MemberInfoResponseDto(member.getName(), member.getEmail(), member.getMemberRole().name());
    }
}

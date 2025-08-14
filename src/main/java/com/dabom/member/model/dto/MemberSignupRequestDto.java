package com.dabom.member.model.dto;

import com.dabom.member.model.entity.Member;

public record MemberSignupRequestDto(String email, String channelName, String password, String memberRole) {
    public Member toEntity(String encodePassword) {
        return Member.builder()
                .email(email)
                .name(channelName)
                .password(encodePassword)
                .memberRole(memberRole)
                .build();
    }
}

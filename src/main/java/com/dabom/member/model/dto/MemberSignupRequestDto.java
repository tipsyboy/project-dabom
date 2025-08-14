package com.dabom.member.model.dto;

import com.dabom.member.model.entity.Member;

public record MemberSignupRequestDto(String email, String name, String password, String memberRole) {
    public Member toEntity(String encodePassword) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(encodePassword)
                .memberRole(memberRole)
                .build();
    }
}

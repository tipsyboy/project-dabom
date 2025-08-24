package com.dabom.together.model.dto.response;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.TogetherJoinMember;

public record TogetherMemberInfoResponseDto(Integer memberIdx, String name, Boolean isMaster) {
    public static TogetherMemberInfoResponseDto toDto(TogetherJoinMember entity, Member master) {
        Member member = entity.getMember();
        return new TogetherMemberInfoResponseDto(member.getIdx(), member.getName(), member.equals(master));
    }
}

package com.dabom.together.model.dto.response;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;

import java.util.List;

public record TogetherMemberListResponseDto(List<TogetherMemberInfoResponseDto> members) {
    public static TogetherMemberListResponseDto toDto(Together together) {
        List<TogetherJoinMember> memberlist = together.getMembers();
        Member master = together.getMaster();

        List<TogetherMemberInfoResponseDto> list =
                memberlist.stream().map((member) ->
                        TogetherMemberInfoResponseDto.toDto(member, master))
                        .toList();

        return new TogetherMemberListResponseDto(list);
    }
}

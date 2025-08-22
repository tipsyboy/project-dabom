package com.dabom.together.model.dto.request;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;
import lombok.Getter;

@Getter
public class TogetherJoinMemberRequestDto {
    private Integer togetherIdx;

    public TogetherJoinMember toEntity(Together together, Member member) {
        return TogetherJoinMember.builder()
                .together(together)
                .member(member)
                .isJoin(true)
                .isDelete(false)
                .build();
    }
}

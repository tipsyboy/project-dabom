package com.dabom.together.model.dto;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;
import lombok.Getter;

@Getter
public class TogetherJoinWithCodeRequestDto {
    private Integer togetherIdx;
    private String code;

    public TogetherJoinMember toEntity(Together together, Member member) {
        return TogetherJoinMember.builder()
                .together(together)
                .member(member)
                .isJoin(true)
                .isDelete(false)
                .build();
    }
}

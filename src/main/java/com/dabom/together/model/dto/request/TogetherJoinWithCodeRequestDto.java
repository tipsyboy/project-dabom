package com.dabom.together.model.dto.request;

import lombok.Getter;

@Getter
public class TogetherJoinWithCodeRequestDto extends TogetherJoinMemberRequestDto {
    private Integer togetherIdx;
    private String code;
}

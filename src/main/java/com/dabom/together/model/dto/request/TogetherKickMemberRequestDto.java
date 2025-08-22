package com.dabom.together.model.dto.request;

import lombok.Getter;

@Getter
public class TogetherKickMemberRequestDto {
    private Integer togetherIdx;
    private Integer kickedMemberIdx;
}

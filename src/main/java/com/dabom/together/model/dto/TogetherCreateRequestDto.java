package com.dabom.together.model.dto;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.Together;
import lombok.Getter;

@Getter
public class TogetherCreateRequestDto {
    private String title;
    private String videoUrl;
    private Integer maxMemberNumber;
    private Boolean isOpen;

    public Together toEntity(Member member) {
        return Together.builder()
                .title(title)
                .videoUrl(videoUrl)
                .maxMemberNum(maxMemberNumber)
                .isOpen(isOpen)
                .isDelete(false)
                .master(member)
                .build();
    }
}

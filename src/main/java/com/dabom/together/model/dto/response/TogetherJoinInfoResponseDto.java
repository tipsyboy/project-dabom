package com.dabom.together.model.dto.response;

import com.dabom.together.model.entity.Together;

public record TogetherJoinInfoResponseDto(Integer togetherIdx, String videoUrl, String title,
                                          TogetherMemberListResponseDto members) {
    public static TogetherJoinInfoResponseDto toDto(Together together) {
        return new TogetherJoinInfoResponseDto(together.getIdx(), together.getVideoUrl(),
                together.getTitle(), TogetherMemberListResponseDto.toDto(together));
    }
}

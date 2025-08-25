package com.dabom.together.model.dto.response;

public record TogetherMasterResponseDto(Boolean isMaster) {
    public static TogetherMasterResponseDto isMasterMember() {
        return new TogetherMasterResponseDto(true);
    }

    public static TogetherMasterResponseDto isMember() {
        return new TogetherMasterResponseDto(true);
    }
}

package com.dabom.channelboard.model.dto;


import com.dabom.channelboard.model.entity.ChannelBoard;
import com.dabom.member.model.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채널 게시글 등록 요청 DTO")
public record ChannelBoardRegisterRequestDto(

        @Schema(description = "게시글 제목", example = "첫 번째 공지사항")
        String title,

        @Schema(description = "게시글 내용", example = "안녕하세요. 채널에 오신 것을 환영합니다!")
        String contents
) {
    public ChannelBoard toEntity(Member channel) {
        return ChannelBoard.builder()
                .title(title)
                .contents(contents)
                .channel(channel)
                .build();
    }
}

package com.dabom.channelboard.model.dto;


import com.dabom.channelboard.model.entity.ChannelBoard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "채널 게시글 수정 요청 DTO")
public class ChannelBoardUpdateRequestDto {

    @Schema(description = "게시글 고유 ID", example = "1")
    private Integer boardIdx;

    @Schema(description = "게시글 제목", example = "공지사항 수정 제목")
    private String title;

    @Schema(description = "게시글 내용", example = "내용을 수정했습니다.")
    private String contents;

    @Schema(description = "소프트 삭제 여부", example = "false")
    private Boolean isDeleted;

    public ChannelBoard toEntity() {
        return ChannelBoard.builder()
                .idx(boardIdx)
                .title(title)
                .contents(contents)
                .build();
    }

    public ChannelBoard softDelete(ChannelBoard entity) {
        entity.setIsDeleted(true);
        return entity;
    }
}


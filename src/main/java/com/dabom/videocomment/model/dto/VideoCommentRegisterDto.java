package com.dabom.videocomment.model.dto;

import com.dabom.member.model.entity.Member;
import com.dabom.video.model.Video;
import com.dabom.videocomment.model.entity.VideoComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class VideoCommentRegisterDto {
    @Schema(description = "비디오 댓글", required = true, example = "댓글 내용")
    private String content;

    public VideoComment toEntity(Video video, Member member) {
        return VideoComment.builder()
                .content(content)
                .video(video)
                .member(member) // member 추가
                .build();
    }
}

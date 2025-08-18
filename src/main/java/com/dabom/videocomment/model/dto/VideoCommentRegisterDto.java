package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class VideoCommentRegisterDto {
    @Schema(description = "비디오 댓글", required = true, example = "댓글 내용")
    private String content;
    private String  username;
    private Integer videoIdx;
    private Integer userIdx;

    public VideoComment toEntity(){
//        Video video = Video.builder()
//                .idx(videoIdx)
//                .build();
//
//        User user = User.builder()
//                .idx(userIdx)
//                .build();

        VideoComment entity = VideoComment.builder()
                .content(content)
//                .video(video)
//                .user(user)
                .isDeleted(false)
                .build();
        return entity;
    }
}

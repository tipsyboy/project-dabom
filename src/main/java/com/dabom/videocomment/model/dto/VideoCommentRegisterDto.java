package com.dabom.videocomment.model.dto;

import com.dabom.member.model.entity.Member;
import com.dabom.video.model.Video;
import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoCommentRegisterDto {
    private String content;

    public VideoComment toEntity(Video video, Member member) {
        return VideoComment.builder()
                .content(this.content)
                .video(video)
                .member(member)
                .build();
    }
}
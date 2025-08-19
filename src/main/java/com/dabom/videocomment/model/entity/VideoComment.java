package com.dabom.videocomment.model.entity;

import com.dabom.common.BaseEntity;
import com.dabom.member.model.entity.Member;
import com.dabom.video.model.Video;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String content;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "video_idx")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @Builder
    public VideoComment(String content, Video video, Member member, Boolean isDeleted) {
        this.content = content;
        this.video = video;
        this.member = member;
        this.isDeleted = false;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    // DTO 변환용
    public static VideoComment from(VideoComment entity) {
        return VideoComment.builder()
                .content(entity.getContent())
                .video(entity.getVideo())
                .member(entity.getMember())
                .build();
    }
}

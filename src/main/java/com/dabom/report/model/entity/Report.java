package com.dabom.report.model.entity;

import com.dabom.boardcomment.model.entity.BoardComment;
import com.dabom.channelboard.model.entity.ChannelBoard;
import com.dabom.member.model.entity.Member;
import com.dabom.video.model.Video;
import com.dabom.videocomment.model.entity.VideoComment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String reason;
    private Integer reportcount;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "video_idx")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "videocomment_idx")
    private VideoComment videoComment;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private ChannelBoard channelBoard;

    @ManyToOne
    @JoinColumn(name = "boardcomment_idx")
    private BoardComment boardComment;
}

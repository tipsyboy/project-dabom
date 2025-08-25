package com.dabom.playlist.model.entity;

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
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String playlistTitle;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "video_idx")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @Builder
    public Playlist(String playlistTitle, Boolean isDeleted, Video video, Member member) {
        this.playlistTitle = playlistTitle;
        this.isDeleted = isDeleted;
        this.video = video;
        this.member = member;

    }

    public void delete() {
        this.isDeleted = true;
    }

    public void updateContent(String playlistTitle, Video video) {
        this.playlistTitle = playlistTitle;
        this.video = video;
    }

}

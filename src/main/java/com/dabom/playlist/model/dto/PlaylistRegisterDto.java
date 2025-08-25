package com.dabom.playlist.model.dto;

import com.dabom.member.model.entity.Member;
import com.dabom.playlist.model.entity.Playlist;
import com.dabom.video.model.Video;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistRegisterDto {
    private String playlistTitle;

    public Playlist toEntity(Video video, Member member){
        return Playlist.builder()
                .playlistTitle(this.playlistTitle)
                .video(video)
                .member(member)
                .build();
    }
}

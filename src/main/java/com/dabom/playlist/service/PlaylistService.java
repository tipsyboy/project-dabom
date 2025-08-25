package com.dabom.playlist.service;

import com.dabom.member.model.entity.Member;
import com.dabom.member.repository.MemberRepository;
import com.dabom.playlist.model.dto.PlaylistRegisterDto;
import com.dabom.playlist.model.entity.Playlist;
import com.dabom.playlist.repository.PlaylistRepository;
import com.dabom.video.model.Video;
import com.dabom.video.repository.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final MemberRepository memberRepository;
    private final VideoRepository videoRepository;


    @Transactional
    public Integer register(PlaylistRegisterDto dto, Integer videoIdx, Integer memberIdx) {
        Video video = videoRepository.findById(videoIdx)
                .orElseThrow(() -> new EntityNotFoundException("영상을 찾을 수 없습니다: " + videoIdx));

        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다: " + memberIdx));
        Playlist playlist = dto.toEntity(video, member);
        return playlistRepository.save(playlist).getIdx();
    }
}

package com.dabom.together.service;

import com.dabom.member.model.entity.Member;
import com.dabom.member.repository.MemberRepository;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.together.model.dto.request.*;
import com.dabom.together.model.dto.response.TogetherInfoResponseDto;
import com.dabom.together.model.dto.response.TogetherListResponseDto;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;
import com.dabom.together.repository.TogetherJoinMemberRepository;
import com.dabom.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TogetherService {
    private final TogetherRepository togetherRepository;
    private final TogetherJoinMemberRepository togetherJoinMemberRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TogetherInfoResponseDto createTogether(TogetherCreateRequestDto dto, MemberDetailsDto memberDetailsDto) {
        Member member = memberRepository.findById(memberDetailsDto.getIdx()).orElseThrow();
        Together together = togetherRepository.save(dto.toEntity(member));
        return TogetherInfoResponseDto.toDto(together);
    }

    public TogetherListResponseDto getTogetherList() {
        List<Together> togethers = togetherRepository.findAll();
        return TogetherListResponseDto.toDto(togethers);
    }

    public TogetherListResponseDto searchTogetherList(TogetherSearchRequestDto dto) {
        List<Together> togethers = togetherRepository.findAll();
        return TogetherListResponseDto.toDto(togethers);
    }

    @Transactional
    public TogetherInfoResponseDto changeTogetherTitle(Integer togetherIdx,
                                                       TogetherChangeTitleRequestDto dto,
                                                       MemberDetailsDto memberDetailsDto) {
        Together together = togetherRepository.findById(togetherIdx).orElseThrow();
        Member member = memberRepository.findById(memberDetailsDto.getIdx()).orElseThrow();

        if(!together.getMaster().equals(member)) {
            throw new RuntimeException();
        }
        together.changeTitle(dto.getTitle());
        Together save = togetherRepository.save(together);
        return TogetherInfoResponseDto.toDto(save);
    }

    @Transactional
    public void changeMaxJoinMember() {

    }

    @Transactional
    public void kickTogetherMember(TogetherKickMemberRequestDto dto) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();
        Member member = memberRepository.findById(dto.getKickedMemberIdx()).orElseThrow();

        TogetherJoinMember kickMember = togetherJoinMemberRepository.findByMemberAndTogether(member, together).orElseThrow();
        kickMember.expel();

        togetherJoinMemberRepository.save(kickMember);
    }

    @Transactional
    public void deleteTogether(TogetherDeleteRequestDto dto, Member member) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();

        if(!member.equals(together.getMaster())) {
            throw new RuntimeException();
        }
        together.deleteTogether();

        togetherRepository.save(together);
    }
}

package com.dabom.together.service;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.dto.request.TogetherJoinDeleteRequestDto;
import com.dabom.together.model.dto.response.TogetherJoinInfoResponseDto;
import com.dabom.together.model.dto.request.TogetherJoinMemberRequestDto;
import com.dabom.together.model.dto.request.TogetherJoinWithCodeRequestDto;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;
import com.dabom.together.repository.TogetherJoinMemberRepository;
import com.dabom.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TogetherJoinMemberService {
    private final TogetherJoinMemberRepository togetherJoinMemberRepository;
    private final TogetherRepository togetherRepository;

    @Transactional
    public void joinTogetherMember(TogetherJoinMemberRequestDto dto, Member member) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();
        isPublicTogether(together);
        joinTogetherMember(dto, member, together);
    }

    @Transactional
    public void joinTogetherWithCodeMember(TogetherJoinWithCodeRequestDto dto, Member member) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();
        validInvitedCode(dto, together);
        joinTogetherMember(dto, member, together);
    }

    public TogetherJoinInfoResponseDto loginTogetherMember(TogetherJoinMemberRequestDto dto, Member member) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();
        TogetherJoinMember togetherJoinMember
                = togetherJoinMemberRepository.findByMemberAndTogether(member, together).orElseThrow();

        return TogetherJoinInfoResponseDto.toDto(together);
    }

    @Transactional
    public void leaveTogetherMember(TogetherJoinDeleteRequestDto dto, Member member) {
        Together together = togetherRepository.findById(dto.getTogetherIdx()).orElseThrow();
        TogetherJoinMember togetherJoinMember
                = togetherJoinMemberRepository.findByMemberAndTogether(member, together).orElseThrow();

        togetherJoinMember.leaveTogether();

        togetherJoinMemberRepository.save(togetherJoinMember);
    }

    private void isPublicTogether(Together together) {
        if(!together.getIsOpen()) {
            throw new RuntimeException();
        }
    }

    private void joinTogetherMember(TogetherJoinMemberRequestDto dto, Member member, Together together) {
        if(together.getMaxMemberNum() > together.getJoinMemberNum()){
            TogetherJoinMember entity = dto.toEntity(together, member);
            togetherJoinMemberRepository.save(entity);
            together.joinMember();
            togetherRepository.save(together);
        }
    }

    private void validInvitedCode(TogetherJoinWithCodeRequestDto dto, Together together) {
        if(!together.getInvitedCode().equals(dto.getCode())) {
            throw new RuntimeException();
        }
    }
}

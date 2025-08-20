package com.dabom.together.service;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.dto.TogetherCreateRequestDto;
import com.dabom.together.repository.TogetherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogetherService {
    private final TogetherRepository togetherRepository;

    public void createTogether(TogetherCreateRequestDto dto, Member member) {
        togetherRepository.save(dto.toEntity(member));
    }


}

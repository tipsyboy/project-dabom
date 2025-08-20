package com.dabom.together.service;

import com.dabom.together.repository.TogetherJoinMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogetherJoinMemberService {
    private final TogetherJoinMemberRepository togetherJoinMemberRepository;

}

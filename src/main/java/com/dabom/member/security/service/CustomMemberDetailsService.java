package com.dabom.member.security.service;

import com.dabom.member.model.entity.Member;
import com.dabom.member.repository.MemberRepository;
import com.dabom.member.security.dto.MemberDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {
    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = repository.findByEmail(username);
        if(optionalMember.isEmpty()) {
            throw new RuntimeException();
        }
        Member member = optionalMember.get();

        return MemberDetailsDto.builder()
                .member(member)
                .build();
    }
}

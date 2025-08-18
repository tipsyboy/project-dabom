package com.dabom.member.util;

import com.dabom.member.model.dto.MemberSignupRequestDto;
import com.dabom.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final MemberService memberService;

    @PostConstruct
    public void initData() {
        MemberSignupRequestDto dto1 = new MemberSignupRequestDto("DabomTotalManager@dabom.com", "DabomTopManager", "Dabom!234", "MANAGER");
        MemberSignupRequestDto dto2 = new MemberSignupRequestDto("DabomSubManager@dabom.com", "DabomSubManager", "Dabom1@34", "MANAGER");
        memberService.signUpMember(dto1);
        memberService.signUpMember(dto2);
    }
}

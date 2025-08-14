package com.dabom.member.controller;

import com.dabom.common.BaseResponse;
import com.dabom.member.model.dto.MemberInfoResponseDto;
import com.dabom.member.model.dto.MemberLoginRequestDto;
import com.dabom.member.model.dto.MemberSignupRequestDto;
import com.dabom.member.model.dto.MemberUpdateNameRequestDto;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.dabom.member.contants.JWTConstants.ACCESS_TOKEN;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> signupMember(@RequestBody MemberSignupRequestDto dto) {
        memberService.signUpMember(dto);
        return ResponseEntity.ok(BaseResponse.of("회원 가입 성공", HttpStatus.OK));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> loginMember(@RequestBody MemberLoginRequestDto dto) {
        String jwt = memberService.loginMember(dto);
        if (jwt != null) {
            return ResponseEntity.ok()
                    .header("Set-Cookie", ACCESS_TOKEN+"=" + jwt + "; HttpOnly; Secure; Path=/;")
                    .body(BaseResponse.of("로그인 성공", HttpStatus.OK));
        }
        return ResponseEntity.status(400).body(BaseResponse.of("로그인 실패", HttpStatus.BAD_REQUEST));
    }

    @PatchMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateNameMember(@RequestBody MemberUpdateNameRequestDto dto,
                                           @AuthenticationPrincipal MemberDetailsDto memberDetailsDto) {
        memberService.updateMemberName(memberDetailsDto, dto);
        return ResponseEntity.ok(BaseResponse.of("회원 이름 변경 성공", HttpStatus.OK));
    }

    @GetMapping("/info")
    public ResponseEntity<BaseResponse<MemberInfoResponseDto>> readMemberInfo(@AuthenticationPrincipal MemberDetailsDto dto) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.readMemberInfo(dto);
        return ResponseEntity.ok(BaseResponse.of(memberInfoResponseDto, HttpStatus.OK));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> deleteMember(@AuthenticationPrincipal MemberDetailsDto dto) {
        memberService.deleteMember(dto);
        return ResponseEntity.ok(BaseResponse.of("삭제 완료 되었습니다.", HttpStatus.OK));
    }
}

package com.dabom.member.controller;

import com.dabom.common.BaseResponse;
import com.dabom.member.model.dto.*;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.dabom.member.contants.JWTConstants.ACCESS_TOKEN;

@RestController
@RequestMapping("/api/member")
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
            ResponseCookie accessTokenCookie = ResponseCookie.from(ACCESS_TOKEN, jwt)
                    .httpOnly(true)
                    .secure(false)
//                    .sameSite("None")
                    .path("/")
                    .maxAge(60 * 60) // 1시간
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                    .body(BaseResponse.of("로그인 성공", HttpStatus.OK));
        }
        return ResponseEntity.status(400).body(BaseResponse.of("로그인 실패", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logoutMember() {
        ResponseCookie deleteAccessToken = ResponseCookie.from(ACCESS_TOKEN, "")
                .httpOnly(true)
                .secure(true)
//                .sameSite("None")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteAccessToken.toString())
                .body(BaseResponse.of("로그아웃 성공", HttpStatus.OK));
    }

    @PostMapping("/exists/email")
    public ResponseEntity<BaseResponse<MemberEmailCheckResponseDto>> checkEmail(@RequestBody MemberEmailCheckRequestDto dto) {
        MemberEmailCheckResponseDto memberEmailCheckResponseDto = memberService.checkMemberEmail(dto.email());
        return ResponseEntity.status(200).body(BaseResponse.of(memberEmailCheckResponseDto, HttpStatus.OK));
    }

    @PostMapping("/exists/channel")
    public ResponseEntity<BaseResponse<MemberChannelNameCheckResponseDto>> checkChannelName(String channelName) {
        MemberChannelNameCheckResponseDto memberChannelNameCheckResponseDto = memberService.checkMemberChannelName(channelName);
        return ResponseEntity.status(200).body(BaseResponse.of(memberChannelNameCheckResponseDto, HttpStatus.OK));
    }

    @PatchMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateNameMember(@RequestBody MemberUpdateChannelRequestDto dto,
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
        return ResponseEntity.ok(BaseResponse.of("삭제 완료 되었습니다. 실제 데이터 삭제까지는 하루정도 소요됩니다.", HttpStatus.OK));
    }
}

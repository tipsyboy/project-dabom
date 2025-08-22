package com.dabom.together.controller;

import com.dabom.common.BaseResponse;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.member.service.MemberService;
import com.dabom.together.model.dto.request.TogetherChangeTitleRequestDto;
import com.dabom.together.model.dto.request.TogetherCreateRequestDto;
import com.dabom.together.model.dto.request.TogetherSearchRequestDto;
import com.dabom.together.model.dto.response.TogetherInfoResponseDto;
import com.dabom.together.model.dto.response.TogetherListResponseDto;
import com.dabom.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/together")
@RequiredArgsConstructor
public class TogetherController {
    private final TogetherService togetherService;
    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<BaseResponse<TogetherInfoResponseDto>> saveTogether(@RequestBody TogetherCreateRequestDto dto,
                                         @AuthenticationPrincipal MemberDetailsDto memberDetailsDto) {
        TogetherInfoResponseDto togetherDto = togetherService.createTogether(dto, memberDetailsDto);
        return ResponseEntity.ok(BaseResponse.of(togetherDto, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<TogetherListResponseDto>> getTogetherList() {
        TogetherListResponseDto togetherListResponseDto = togetherService.getTogetherList();
        return ResponseEntity.ok(BaseResponse.of(togetherListResponseDto, HttpStatus.OK));
    }

    @PostMapping("/search")
    public ResponseEntity<BaseResponse<TogetherListResponseDto>> searchTogethers(@RequestBody TogetherSearchRequestDto dto) {
        TogetherListResponseDto togetherListResponseDto = togetherService.searchTogetherList(dto);
        return ResponseEntity.ok(BaseResponse.of(togetherListResponseDto, HttpStatus.OK));
    }

    @PatchMapping("/change/{togetherIdx}")
    public ResponseEntity<BaseResponse<TogetherInfoResponseDto>> changeTogetherTitle(@PathVariable Integer togetherIdx,
                                                              @RequestBody TogetherChangeTitleRequestDto dto,
                                                              @AuthenticationPrincipal MemberDetailsDto memberDetailsDto) {
        TogetherInfoResponseDto togetherInfoResponseDto = togetherService.changeTogetherTitle(togetherIdx, dto, memberDetailsDto);
        return ResponseEntity.ok(BaseResponse.of(togetherInfoResponseDto, HttpStatus.OK));
    }
}

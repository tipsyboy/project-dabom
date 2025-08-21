package com.dabom.report.controller;

import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.report.model.dto.ReportRegisterDto;
import com.dabom.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// MemberDetailsDto의 실제 경로에 따라 import 문이 달라질 수 있습니다.

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/register")
    public ResponseEntity<String> registerReport(
            @RequestBody ReportRegisterDto<Integer> reportDto,
            @AuthenticationPrincipal MemberDetailsDto memberDetailsDto) {

        Integer reporterMemberIdx = memberDetailsDto.getIdx();

        try {
            reportService.registerReport(reportDto, reporterMemberIdx);
            return new ResponseEntity<>("Report registered successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during report registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.dabom.search.controller;

import com.dabom.common.BaseEntity;
import com.dabom.common.BaseResponse;
import com.dabom.common.SliceBaseResponse;
import com.dabom.search.model.dto.SearchResponseDto;
import com.dabom.search.service.SearchService;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<SliceBaseResponse<SearchResponseDto>>> list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        SliceBaseResponse<SearchResponseDto> result = searchService.list(page, size);
        return ResponseEntity.ok(
                BaseResponse.of(
                        result, HttpStatus.OK, ""));
    }

    @GetMapping("/videos")
    public ResponseEntity<BaseResponse<SliceBaseResponse<SearchResponseDto>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, ServletResponse servletResponse) {

        SliceBaseResponse<SearchResponseDto> result = searchService.search(keyword, page, size);
        return ResponseEntity.ok(BaseResponse.of(result,HttpStatus.OK,"비디오조회완료"));
    }
}

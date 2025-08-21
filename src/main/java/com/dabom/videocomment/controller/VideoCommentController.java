package com.dabom.videocomment.controller;

import com.dabom.common.BaseResponse;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.videocomment.model.dto.VideoCommentRegisterDto;
import com.dabom.videocomment.model.dto.VideoCommentResponseDto;
import com.dabom.videocomment.model.dto.VideoCommentUpdateDto;
import com.dabom.videocomment.model.entity.VideoComment;
import com.dabom.videocomment.service.VideoCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.dabom.videocomment.constansts.SwaggerConstants.*;

@Tag(name = "영상 하단 게시판 기능")
@RequestMapping("/videocomment")
@RestController
@RequiredArgsConstructor
public class VideoCommentController {

    private final VideoCommentService videoCommentService;

    @Operation(
            summary = "댓글 등록",
            description = "특정 영상에 댓글을 등록한다",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "댓글 등록 요청 데이터",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VideoCommentRegisterDto.class),
                            examples = @ExampleObject(
                                    name = "댓글 등록 요청 예시",
                                    value = VIDEO_COMMENT_CREATED_REQUEST
                            ))))
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(
                            name = "댓글 등록 성공 응답",
                            value = VIDEO_COMMENT_CREATED_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 등록 실패", content = @Content(mediaType = "application/json"))
    @PostMapping("/{videoIdx}/members//comments")
    public ResponseEntity<BaseResponse<Integer>> register(
            @RequestBody VideoCommentRegisterDto dto,
            @PathVariable Integer videoIdx,
            @AuthenticationPrincipal MemberDetailsDto memberDetailsDto) {

        Integer idx = videoCommentService.register(dto, videoIdx, memberDetailsDto.getIdx());

        return ResponseEntity.ok(
                BaseResponse.of(idx, HttpStatus.OK, "댓글이 성공적으로 등록되었습니다.")
        );
    }

    @Operation(
            summary = "댓글 조회(정렬방식 선택)",
            description = "댓글 조회 - 최신순/오래된순"
    )
    @ApiResponse(responseCode = "200", description = "댓글 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(
                            name = "댓글 조회 성공 응답",
                            value = VIDEO_COMMENT_LIST_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 조회 실패", content = @Content(mediaType = "application/json"))
    @GetMapping("/{videoIdx}/comments")
    public ResponseEntity<BaseResponse<Slice<VideoCommentResponseDto>>> list(@PathVariable Integer videoIdx, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Slice<VideoCommentResponseDto> response = videoCommentService.list(videoIdx, pageable);
        return ResponseEntity.ok(
                BaseResponse.of(response, HttpStatus.OK, "댓글 목록 조회 성공")
        );
    }

    @Operation(
            summary = "댓글 수정",
            description = "특정 영상 댓글 수정",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "댓글 수정 요청 데이터",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = VideoCommentUpdateDto.class),
                            examples = @ExampleObject(
                                    name = "댓글 수정 요청 예시",
                                    value = VIDEO_COMMENT_UPDATE_REQUEST
                            ))))
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(
                            name = "댓글 수정 성공 응답",
                            value = VIDEO_COMMENT_UPDATE_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 수정 실패", content = @Content(mediaType = "application/json"))
    @PatchMapping("/{videoIdx}/comments/{commentIdx}")
    public ResponseEntity<BaseResponse<Integer>> update(
            @PathVariable Integer videoIdx,
            @PathVariable Integer commentIdx,
            @RequestBody VideoCommentUpdateDto dto) {

        Integer idx = videoCommentService.update(commentIdx, dto);

        return ResponseEntity.ok(
                BaseResponse.of(idx, HttpStatus.OK, "댓글이 성공적으로 수정되었습니다.")
        );
    }
}

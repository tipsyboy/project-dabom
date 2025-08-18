package com.dabom.videocomment.controller;

import com.dabom.boardcomment.model.dto.BoardCommentCreateRequestDto;
import com.dabom.common.BaseResponse;
import com.dabom.videocomment.model.dto.VideoCommentRegisterDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dabom.boardcomment.constansts.SwaggerConstants.*;
import static com.dabom.videocomment.constansts.SwaggerConstants.*;

@Tag(name = "영상 하단 게시판 기능")
@RequestMapping("/videocomment")
@RestController
@RequiredArgsConstructor
public class VideoCommentController {
    private final VideoCommentService videoCommentService;

    @Operation(
            summary = "영상 하단 댓글 등록",
            description = "영상에 관한 댓글을 등록하는 기능",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "댓글 등록 요청 데이터",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardCommentCreateRequestDto.class),
                            examples = @ExampleObject(
                                    name = "댓글 등록 요청 예시",
                                    value = VIDEO_COMMENT_CREATED_REQUEST
                            )))
    )
    @PostMapping("/register")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(name = "댓글 등록 성공 응답", value = VIDEO_COMMENT_CREATED_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 등록 실패", content = @Content(mediaType = "application/json"))

    public ResponseEntity register(@RequestBody VideoCommentRegisterDto dto) {
        videoCommentService.register(dto);

        return ResponseEntity.status(200).body("success");
    }

    @Operation(
            summary = "댓글 조회(정렬방식 선택)",
            description = "댓글 조회 - 최신순/오래된순"
    )
    @ApiResponse(responseCode = "200", description = "댓글 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(name = "댓글 조회 성공 응답", value = VIDEO_COMMENT_LIST_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 조회 실패", content = @Content(mediaType = "application/json"))
    @GetMapping("/list")
    public ResponseEntity list() {
        List<VideoComment> response = videoCommentService.list();
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "댓글 수정",
            description = "채널 게시글 댓글 수정 기능",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "댓글 수정 요청 데이터",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoardCommentCreateRequestDto.class),
                            examples = @ExampleObject(
                                    name = "댓글 수정 요청 예시",
                                    value = VIDEO_COMMENT_UPDATE_REQUEST
                            ))))
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BaseResponse.class),
                    examples = @ExampleObject(name = "댓글 수정 성공 응답", value = VIDEO_COMMENT_UPDATE_RESPONSE
                    )))
    @ApiResponse(responseCode = "400", description = "댓글 수정 실패", content = @Content(mediaType = "application/json"))

    @PatchMapping("/update")
    public ResponseEntity update(
            @RequestBody VideoCommentUpdateDto dto) {
        videoCommentService.update(dto);
        return ResponseEntity.status(200).body("update success");
    }
}

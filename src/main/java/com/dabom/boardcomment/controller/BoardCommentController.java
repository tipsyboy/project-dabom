package com.dabom.boardcomment.controller;

import com.dabom.common.BaseResponse;
import com.dabom.boardcomment.model.dto.BoardCommentCreateRequestDto;
import com.dabom.boardcomment.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "게시글 댓글")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

//    @Operation(
//            summary = "게시글 댓글 기능",
//            description = "게시글 댓글 동록"
//    )
    @PostMapping("/create/{boardIdx}")
    public ResponseEntity<BaseResponse<Integer>> create(@RequestBody BoardCommentCreateRequestDto dto,
                                                        @PathVariable Integer boardIdx) {
        Integer idx = boardCommentService.create(dto, boardIdx);

        return ResponseEntity.ok(BaseResponse.of(idx, HttpStatus.OK, "ihkljh"));
    }

//    @PostMapping("/update")
//    public ResponseEntity update(@RequestBody ReplyCreateRequestDto dto) {
//        replyService.update(dto);
//
//        return ResponseEntity.ok("굿");
//    }


    //    @Operation(
//            summary = "게시글 댓글 기능",
//            description = "게시글 댓글 삭제"
//    )
    @DeleteMapping("/delete/{commentIdx}")
    public ResponseEntity<Void> delete(@PathVariable Integer commentIdx) {
        boardCommentService.delete(commentIdx);

        return ResponseEntity.ok(null);
    }
}

package com.dabom.boardcomment.controller;

import com.dabom.common.BaseResponse;
import com.dabom.boardcomment.model.dto.BoardCommentCreateRequestDto;
import com.dabom.boardcomment.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;


    @PostMapping("/create/{boardIdx}")
    public ResponseEntity<BaseResponse<Integer>> create(@RequestBody BoardCommentCreateRequestDto dto,
                                                        @PathVariable Integer boardIdx) {
        Integer idx = boardCommentService.create(dto, boardIdx);

        return ResponseEntity.ok(BaseResponse.of(idx, HttpStatus.OK, "ihkljh"));
    }
// 안녕하세요
//    @PostMapping("/update")
//    public ResponseEntity update(@RequestBody ReplyCreateRequestDto dto) {
//        replyService.update(dto);
//
//        return ResponseEntity.ok("굿");
//    }
    @DeleteMapping("/delete/{commentIdx}")
    public ResponseEntity<Void> delete(@PathVariable Integer commentIdx) {
        boardCommentService.delete(commentIdx);

        return ResponseEntity.ok(null);
    }
}

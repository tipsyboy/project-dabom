package com.dabom.videocomment.controller;

import com.dabom.videocomment.model.dto.VideoCommentRegisterDto;
import com.dabom.videocomment.model.dto.VideoCommentUpdateDto;
import com.dabom.videocomment.model.entity.VideoComment;
import com.dabom.videocomment.service.VideoCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/videocomment")
@RestController
@RequiredArgsConstructor
public class VideoCommentRegisterController {
    private final VideoCommentService videoCommentService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody VideoCommentRegisterDto dto) {
        videoCommentService.register(dto);

        return ResponseEntity.status(200).body("success");
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        List<VideoComment> response = videoCommentService.list();
        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity update(@RequestBody VideoCommentUpdateDto dto) {
        videoCommentService.update(dto);
        return ResponseEntity.status(200).body("update success");
    }
}

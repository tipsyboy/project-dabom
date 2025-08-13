package com.dabom.channelboard.controller;

import com.dabom.channelboard.model.dto.ChannelBoardReadResponseDto;
import com.dabom.channelboard.model.dto.ChannelBoardRegisterRequestDto;
import com.dabom.channelboard.model.dto.ChannelBoardUpdateRequestDto;
import com.dabom.channelboard.service.ChannelBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel/board")
@RequiredArgsConstructor
public class ChannelBoardController {
    private final ChannelBoardService channelBoardService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ChannelBoardRegisterRequestDto dto) {
        channelBoardService.register(dto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChannelBoardReadResponseDto>> list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<ChannelBoardReadResponseDto> result = channelBoardService.list(page, size);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/read")
    public ResponseEntity<ChannelBoardReadResponseDto> read(@RequestParam Integer idx) {

        ChannelBoardReadResponseDto result = channelBoardService.read(idx);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody ChannelBoardUpdateRequestDto dto) {
          channelBoardService.update(dto);
          return ResponseEntity.ok("success");
    }

    @GetMapping("/delete")
    public ResponseEntity delete(@RequestParam Integer idx) {
        channelBoardService.delete(idx);
        return ResponseEntity.ok("success");
    }



}

package com.dabom.channelboard.controller;

import com.dabom.channelboard.model.dto.ChannelBoardReadResponseDto;
import com.dabom.channelboard.model.dto.ChannelBoardRegisterRequestDto;
import com.dabom.channelboard.model.dto.ChannelBoardUpdateRequestDto;
import com.dabom.channelboard.service.ChannelBoardService;
import com.dabom.common.BaseResponse;
import com.dabom.common.SliceBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel/board")
@RequiredArgsConstructor
public class ChannelBoardController {
    private final ChannelBoardService channelBoardService;


    @PostMapping("/register")
    public ResponseEntity<BaseResponse<Integer>> register(@RequestBody ChannelBoardRegisterRequestDto dto) {
        Integer result = channelBoardService.register(dto);
        return ResponseEntity.ok(BaseResponse.of(result,HttpStatus.OK));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<SliceBaseResponse<ChannelBoardReadResponseDto>>> list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        SliceBaseResponse<ChannelBoardReadResponseDto> result = channelBoardService.list(page, size);

        return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
    }

    @GetMapping("/read/{boardidx}")
    public ResponseEntity<ChannelBoardReadResponseDto> read(@RequestParam Integer boardidx) {

        ChannelBoardReadResponseDto result = channelBoardService.read(boardidx);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse<Integer>> update(@RequestBody ChannelBoardUpdateRequestDto dto) {
        Integer result = channelBoardService.update(dto);
        return ResponseEntity.ok(BaseResponse.of(result,HttpStatus.OK));
    }

    @GetMapping("/delete/{boardidx}")
    public ResponseEntity<Void> delete(@RequestParam Integer boardidx) {
        channelBoardService.delete(boardidx);
        return ResponseEntity.ok(null);
    }


}

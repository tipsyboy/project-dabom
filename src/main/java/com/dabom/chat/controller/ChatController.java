package com.dabom.chat.controller;

import com.dabom.chat.model.dto.ChatRoomReadResponseDto;
import com.dabom.chat.model.dto.ChatRoomRegisterRequestDto;
import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.chat.serivce.ChatService;
import com.dabom.common.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/room")
    public ResponseEntity<BaseResponse<Long>> createRoom(@RequestBody ChatRoomRegisterRequestDto dto) {
        long result = chatService.createRoom(dto);
        return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
    }

    @GetMapping("/list/{member1_idx}")
    public ResponseEntity<BaseResponse<ChatRoomReadResponseDto>> listRoom(@RequestParam Integer member1_idx) {
        List<ChatRoom> result = chatService.list(member1_idx);
        return null;
    }


}

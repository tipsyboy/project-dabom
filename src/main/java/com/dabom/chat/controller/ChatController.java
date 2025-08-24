package com.dabom.chat.controller;

import com.dabom.chat.model.dto.ChatMessageDto;
import com.dabom.chat.model.dto.ChatRoomListResponseDto;
import com.dabom.chat.model.dto.ChatRoomReadResponseDto;
import com.dabom.chat.model.dto.ChatRoomRegisterRequestDto;
import com.dabom.chat.serivce.ChatService;
import com.dabom.common.BaseResponse;
import com.dabom.common.SliceBaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ChatRoomListResponseDto>>> list(Principal principal) {
        Integer memberidx = chatService.getMember(principal);
        List<ChatRoomListResponseDto> result = chatService.getList(memberidx);
        return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
    }

    @GetMapping("/read/{room_idx}")
    public ResponseEntity<BaseResponse<SliceBaseResponse<ChatRoomReadResponseDto>>> readRoom(
            @PathVariable("room_idx") Long roomIdx,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Principal principal) {
        try {
            Integer memberIdx = chatService.getMember(principal);
            SliceBaseResponse<ChatRoomReadResponseDto> result = chatService.readRoom(roomIdx, memberIdx, page, size);
            return ResponseEntity.ok(BaseResponse.of(result, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponse.of(null, HttpStatus.UNAUTHORIZED, "Authentication failed"));
        }
    }

    @MessageMapping("/send")
    @SendToUser("/queue/messages")
    public ResponseEntity<BaseResponse<Void>> sendMessage(@Payload ChatMessageDto messageDto) {
        chatService.sendMessage(messageDto);

        return ResponseEntity.ok(BaseResponse.of(null, HttpStatus.OK));
    }


}

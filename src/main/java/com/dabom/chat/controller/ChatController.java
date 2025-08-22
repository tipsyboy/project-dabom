package com.dabom.chat.controller;

import com.dabom.chat.model.dto.ChatRoomReadResponseDto;
import com.dabom.chat.model.dto.ChatRoomRegisterRequestDto;
import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.chat.serivce.ChatService;
import com.dabom.common.BaseResponse;
import com.dabom.member.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dabom.member.contants.JWTConstants.TOKEN_IDX;

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

    @GetMapping("/read/{room_idx}")
    public ResponseEntity ReadRoom(@RequestParam Integer room_idx, ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            String token = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) { // 쿠키 이름이 "token"이라고 가정
                    token = cookie.getValue();
                    break;
                }

                if (token == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                Claims claims = JwtUtils.getClaims(token);
                String member_idx = JwtUtils.getValue(claims, TOKEN_IDX);
                //chatService.readRoom(room_idx,member_idx);

            }

        }
        return null;
    }
}

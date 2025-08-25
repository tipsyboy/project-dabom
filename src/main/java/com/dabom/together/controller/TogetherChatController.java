package com.dabom.together.controller;

import com.dabom.member.model.dto.MemberInfoResponseDto;
import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.member.service.MemberService;
import com.dabom.together.model.dto.response.TogetherChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TogetherChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MemberService memberService;

    @MessageMapping("/together/{togetherId}")
    public void sendMessage(Principal principal,
            @DestinationVariable Integer togetherId, @Payload String message) {
        System.out.println(message);

        Authentication authentication = (Authentication)principal;
        MemberDetailsDto memberDetailsDto = (MemberDetailsDto)authentication.getPrincipal();
        MemberInfoResponseDto memberInfo = memberService.getMemberInfo(memberDetailsDto);
        TogetherChatResponseDto res = TogetherChatResponseDto.toDtoBySend(memberInfo.name(), message);

        messagingTemplate.convertAndSend("/topic/together/" + togetherId, res);
    }

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        Principal userPrincipal = headerAccessor.getUser();
        Authentication authentication = (Authentication)userPrincipal;
        MemberDetailsDto memberDetailsDto = (MemberDetailsDto)authentication.getPrincipal();
        MemberInfoResponseDto memberInfo = memberService.getMemberInfo(memberDetailsDto);
        String destination = headerAccessor.getDestination();

        if (destination != null && destination.startsWith("/topic/together/")) {
            // 새로 접속한 사용자를 포함한 모든 구독자에게 환영 메시지 전송
            TogetherChatResponseDto welcome = TogetherChatResponseDto.toDtoByJoin(memberInfo.name());
            messagingTemplate.convertAndSend(destination, welcome);
        }
    }
}

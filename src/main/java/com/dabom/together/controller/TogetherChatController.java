package com.dabom.together.controller;

import com.dabom.member.security.dto.MemberDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TogetherChatController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/together/{togetherId}")
    public void sendMessage(Principal principal,
            @DestinationVariable Integer togetherId, String message) {
        System.out.println(principal.getName());

        Authentication authentication = (Authentication)principal;
        MemberDetailsDto memberDetailsDto = (MemberDetailsDto)authentication.getPrincipal();
        messagingTemplate.convertAndSend("/together/" + togetherId, message);
    }
}

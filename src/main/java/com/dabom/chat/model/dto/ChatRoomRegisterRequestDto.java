package com.dabom.chat.model.dto;

import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.member.model.entity.Member;
import lombok.Getter;

@Getter
public class ChatRoomRegisterRequestDto {

    private Member member1;
    private Member member2;

    public ChatRoom toEntity(){
        return ChatRoom.builder()
                .member1(this.member1)
                .member2(this.member2)
                .build();
    }
}

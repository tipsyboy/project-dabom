package com.dabom.chat.model.dto;

import com.dabom.chat.model.entity.Chat;
import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatRoomListResponseDto extends BaseEntity {
    private Long idx;
    private Integer member1Idx;
    private String member1Name;
    private Integer member2Idx;
    private String member2Name;
    private String lastMessage;
    private Boolean isLastMessageRead;
    private LocalDateTime lastMessageTime;
    private Boolean isDeleted;

    public static ChatRoomListResponseDto fromEntity(ChatRoom chatRoom, Chat lastChat) {
        return ChatRoomListResponseDto.builder()
                .idx(chatRoom.getIdx())
                .member1Idx(chatRoom.getMember1().getIdx())
                .member1Name(chatRoom.getMember1().getName())
                .member2Idx(chatRoom.getMember2().getIdx())
                .member2Name(chatRoom.getMember2().getName())
                .lastMessage(lastChat != null ? lastChat.getMessage() : null)
                .isLastMessageRead(lastChat != null ? lastChat.getIsRead() : null)
                .lastMessageTime(lastChat != null ? lastChat.getCreatedAt() : null)
                .isDeleted(chatRoom.getIsDeleted())
                .build();
    }

}

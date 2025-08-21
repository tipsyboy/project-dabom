package com.dabom.chat.model.dto;

import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ChatRoomReadResponseDto extends BaseEntity {
    private Long idx;
    private Integer member1Idx;
    private String member1Name;
    private Integer member2Idx;
    private String member2Name;
    private Boolean isDeleted;

    public static ChatRoomReadResponseDto fromEntity(ChatRoom entity) {
        return ChatRoomReadResponseDto.builder()
                .idx(entity.getIdx())
                .member1Idx(entity.getMember1().getIdx())
                .member1Name(entity.getMember1().getName())
                .member2Idx(entity.getMember2().getIdx())
                .member2Name(entity.getMember2().getName())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}

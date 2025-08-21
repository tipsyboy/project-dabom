package com.dabom.chat.serivce;

import com.dabom.chat.model.dto.ChatRoomRegisterRequestDto;
import com.dabom.chat.model.entity.ChatRoom;
import com.dabom.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    public long createRoom(ChatRoomRegisterRequestDto dto) {
        ChatRoom result = chatRoomRepository.save(dto.toEntity());
        return result.getIdx();
    }

    public List<ChatRoom> list(Integer member1Idx) {
        return chatRoomRepository.findAllByMember1IdxAndIsDeleted(member1Idx,false);

    }
}

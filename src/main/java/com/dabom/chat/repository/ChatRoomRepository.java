package com.dabom.chat.repository;

import com.dabom.chat.model.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    List<ChatRoom> findAllByMember1IdxAndIsDeleted(Integer member1Idx, Boolean isDeleted);
}

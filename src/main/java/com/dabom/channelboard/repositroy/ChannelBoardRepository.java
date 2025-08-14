package com.dabom.channelboard.repositroy;

import com.dabom.channelboard.model.entity.ChannelBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelBoardRepository extends JpaRepository<ChannelBoard, Integer> {
    Optional<ChannelBoard> findByIdx(Integer idx);
}

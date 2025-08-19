package com.dabom.channelboard.repositroy;

import com.dabom.channelboard.model.entity.ChannelBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChannelBoardRepository extends JpaRepository<ChannelBoard, Integer> {
    Optional<ChannelBoard> findByIdx(Integer idx);

    //무한 스크롤용 슬라이스 메서드 입당
            // 오름차쑨
    Slice<ChannelBoard> findAllByIsDeletedFalseOrderByIdxAsc(
             Pageable pageable);

    //무한 스크롤용 슬라이스 메서드 입당2
        //내림차쑨
    Slice<ChannelBoard> findAllByIsDeletedFalseOrderByIdxDesc(
             Pageable pageable);

    Long countByIsDeletedFalse();

    @Query("SELECT COUNT(bc) FROM BoardComment bc " +
           "WHERE bc.channelBoard.idx = :boardIdx AND bc.isDeleted = false")
    Long countCommentsByBoardIdx(Integer boardIdx);
}

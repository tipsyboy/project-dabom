package com.dabom.videocomment.repository;

import com.dabom.videocomment.model.entity.VideoComment;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Integer> {

    List<VideoComment> findByVideo_IdxAndIsDeletedFalse(Integer videoIdx);

    Slice<VideoComment> findByVideo_IdxAndIsDeletedFalse(Integer videoIdx, Pageable pageable);

    Slice<VideoComment> findByVideo_IdxAndIsDeletedFalseOrderByLikesDesc(Integer videoIdx, Pageable pageable);
}
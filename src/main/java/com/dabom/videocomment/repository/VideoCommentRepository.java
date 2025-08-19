package com.dabom.videocomment.repository;

import com.dabom.videocomment.model.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCommentRepository extends JpaRepository<VideoComment, Integer> {

    List<VideoComment> findByVideo_IdxAndIsDeletedFalse(Integer videoIdx);
}

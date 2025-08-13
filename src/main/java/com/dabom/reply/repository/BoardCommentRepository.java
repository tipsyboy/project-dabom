package com.dabom.reply.repository;

import com.dabom.reply.model.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
}

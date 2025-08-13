package com.dabom.boardcomment.repository;

import com.dabom.boardcomment.model.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {
}

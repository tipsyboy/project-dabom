package com.dabom.videocomment.repository;

import com.dabom.videocomment.model.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoCommentRepository extends JpaRepository<VideoComment, Integer> {

}

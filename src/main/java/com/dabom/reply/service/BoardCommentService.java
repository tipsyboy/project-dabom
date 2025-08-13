package com.dabom.reply.service;

import com.dabom.reply.model.dto.BoardCommentCreateRequestDto;
import com.dabom.reply.model.dto.BoardCommentListResponseDto;
import com.dabom.reply.model.entity.BoardComment;
import com.dabom.reply.repository.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    public Integer create(BoardCommentCreateRequestDto dto, Integer idx) {
        return boardCommentRepository.save(dto.toEntity()).getIdx();
    }

    public void delete(Integer idx) {
        Optional<BoardComment> result = boardCommentRepository.findById(idx);

        if(result.isPresent()){
            BoardComment entity = result.get();
            entity.delete();
            boardCommentRepository.save(entity);
        }
    }
}

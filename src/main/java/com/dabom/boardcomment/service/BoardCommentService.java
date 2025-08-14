package com.dabom.boardcomment.service;

import com.dabom.boardcomment.model.dto.BoardCommentCreateRequestDto;
import com.dabom.boardcomment.model.entity.BoardComment;
import com.dabom.boardcomment.repository.BoardCommentRepository;
import com.dabom.channelboard.model.entity.ChannelBoard;
import com.dabom.channelboard.repositroy.ChannelBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final ChannelBoardRepository channelBoardRepository;

    public Integer create(BoardCommentCreateRequestDto dto, Integer boardIdx) {

        ChannelBoard board = channelBoardRepository.findById(boardIdx)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다: " + boardIdx));

        BoardComment comment = dto.toEntity(board);
        return boardCommentRepository.save(comment).getIdx();
    }

    public void delete(Integer idx) {
        Optional<BoardComment> result = boardCommentRepository.findById(idx);

        if(result.isPresent()){
            BoardComment entity = result.get();
            entity.delete();
            boardCommentRepository.save(entity);
        } else {
            throw new EntityNotFoundException("댓글을 찾을 수 없습니다: " + idx);
        }

    }
}

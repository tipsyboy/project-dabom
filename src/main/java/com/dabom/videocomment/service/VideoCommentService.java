package com.dabom.videocomment.service;

import com.dabom.videocomment.model.dto.VideoCommentRegisterDto;
import com.dabom.videocomment.model.dto.VideoCommentUpdateDto;
import com.dabom.videocomment.model.entity.VideoComment;
import com.dabom.videocomment.repository.VideoCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoCommentService {
    private final VideoCommentRepository videoCommentRepository;

    public void register(VideoCommentRegisterDto dto){
        VideoComment videoComment = videoCommentRepository.save(dto.toEntity());
    }

    public void deleted(Integer idx){
        Optional<VideoComment> result = videoCommentRepository.findById(idx);
        if(result.isPresent()){
            VideoComment videoComment = result.get();
            videoComment.commentDeleted();
            videoCommentRepository.save(videoComment);
        }
        else throw new RuntimeException();
    }

    public List<VideoComment> list() {
        List<VideoComment> result = videoCommentRepository.findAll();

        return result.stream().map(VideoComment::from).toList();
    }

    public void update(VideoCommentUpdateDto dto){
        VideoComment entity = videoCommentRepository.findById(dto.getIdx())
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다. id: " + dto.getIdx()));
        VideoComment updatedEntity = dto.toEntity(entity);
        videoCommentRepository.save(updatedEntity);
    }
}

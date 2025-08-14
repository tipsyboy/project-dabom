package com.dabom.channelboard.service;

import com.dabom.channelboard.model.dto.ChannelBoardReadResponseDto;
import com.dabom.channelboard.model.dto.ChannelBoardRegisterRequestDto;
import com.dabom.channelboard.model.dto.ChannelBoardUpdateRequestDto;
import com.dabom.channelboard.model.entity.ChannelBoard;
import com.dabom.channelboard.repositroy.ChannelBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelBoardService {
    private final ChannelBoardRepository channelBoardRepository;

    public Integer register(ChannelBoardRegisterRequestDto dto) {
        ChannelBoard result = channelBoardRepository.save(dto.toEntity());
        return result.getIdx();
    }

    public List<ChannelBoardReadResponseDto> list(Integer page, Integer size) {
        //Slice<ChannelBoardListResponseDto> response = result.map(ChannelBoardListResponseDto::from);
        //무한스크롤을 위한 임시 코드;
        List<ChannelBoard> result = channelBoardRepository.findAll();

        return result.stream().map(ChannelBoardReadResponseDto::from).toList();
    }

    public ChannelBoardReadResponseDto read(Integer idx) {
        Optional<ChannelBoard> result = channelBoardRepository.findById(idx);
        if (result.isPresent()) {
            return ChannelBoardReadResponseDto.from(result.get());
        } else {
            throw new EntityNotFoundException("해당 게시글이 존재하지 않습니다: " + idx);
        }
    }

    public Integer update(ChannelBoardUpdateRequestDto dto) {
        ChannelBoard result = channelBoardRepository.save(dto.toEntity());
        return result.getIdx();
    }

    public void delete(Integer idx) {
        Optional<ChannelBoard> result = channelBoardRepository.findById(idx);

        if (result.isPresent()) {
            ChannelBoard board = result.get();
            ChannelBoardUpdateRequestDto dto = new ChannelBoardUpdateRequestDto();
            ChannelBoard deleteBoard = dto.softDelete(board);
            channelBoardRepository.save(deleteBoard);
        } else {
            throw new EntityNotFoundException("해당 게시글이 존재하지 않습니다: " + idx);
        }

    }
}

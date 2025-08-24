package com.dabom.search.service;

import com.dabom.common.SliceBaseResponse;
import com.dabom.search.model.dto.SearchResponseDto;
import com.dabom.video.model.Video;
import com.dabom.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final VideoRepository videoRepository;

    public SliceBaseResponse<SearchResponseDto> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<Video> videoSlice = videoRepository.findVisibleVideosOrderByCreatedAtDesc(pageable);
        // VideoStatus videoStatus 매게 변수에 넣어주기

        List<SearchResponseDto> result = videoSlice.stream().map(SearchResponseDto::from)
                .toList();
        return new SliceBaseResponse<SearchResponseDto>(result, videoSlice.hasNext());
    }

    public SliceBaseResponse<SearchResponseDto> search(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<Video> videoSlice;

        if (keyword == null || keyword.trim().isEmpty()) {
            return list(page, size);
        }

        videoSlice = videoRepository.searchByKeywordOrderByRelevance(keyword.trim(), pageable);

        List<SearchResponseDto> result = videoSlice.stream()
                .map(SearchResponseDto::from)
                .toList();
        return new SliceBaseResponse<>(result, videoSlice.hasNext());
    }

}

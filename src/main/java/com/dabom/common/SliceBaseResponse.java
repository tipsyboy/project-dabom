package com.dabom.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SliceBaseResponse<T> {
    private List<T> content;
    private boolean hasNext;
    private Long totalCount;

    public SliceBaseResponse(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
        this.totalCount = null;
    }
}
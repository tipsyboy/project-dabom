package com.dabom.common;

import lombok.Getter;

import java.util.List;
@Getter
public class SliceBaseResponse<T> {
    private List<T> content;
    private boolean hasNext;

    public SliceBaseResponse(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }
}

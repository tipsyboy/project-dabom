package com.dabom.common;

import java.util.List;

public class SliceBaseResponse<T> {
    private List<T> content;
    private boolean hasNext;

    public SliceBaseResponse(List<T> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }
}

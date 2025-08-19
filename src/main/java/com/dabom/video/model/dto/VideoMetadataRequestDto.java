package com.dabom.video.model.dto;


import com.dabom.video.model.Video;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoMetadataRequestDto {

    private String title;
    private String description;
    private boolean isVisibility;

    public Video toEntity() {
        return Video.builder()
                .title(title)
                .description(description)
                .isVisibility(isVisibility)
                .build();
    }
}

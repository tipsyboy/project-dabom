package com.dabom.video.model.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoMetadataRequestDto {

    private Integer idx;
    private String title;
    private String description;
    private boolean isVisibility;

}

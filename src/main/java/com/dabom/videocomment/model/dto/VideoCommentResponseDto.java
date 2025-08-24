package com.dabom.videocomment.model.dto;

import com.dabom.videocomment.model.entity.VideoComment;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class VideoCommentResponseDto {
    private Integer idx;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Boolean isModified;
    private Integer likes;
    private Integer memberIdx; // 프론트엔드에서 삭제 권한 확인용
    private String username; // 프론트엔드 표시용 (Member의 name 필드 매핑)

    public static VideoCommentResponseDto from(VideoComment entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return VideoCommentResponseDto.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt().format(formatter))
                .updatedAt(entity.getUpdatedAt().format(formatter))
                .isModified(!entity.getCreatedAt().equals(entity.getUpdatedAt()))
                .likes(entity.getLikes())
                .memberIdx(entity.getMember().getIdx())
                .username(entity.getMember().getName()) // Member의 getName() 사용
                .build();
    }
}
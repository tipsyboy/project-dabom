package com.dabom.videocomment.model.entity;


import com.dabom.videocomment.model.dto.VideoCommentResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    @Setter
    private String content;
    private Boolean isDeleted;

    public static VideoComment from(VideoComment entity){
        VideoComment dto = VideoComment.builder()
                .idx(entity.getIdx())
                .content(entity.getContent())
                .build();

        return dto;
    }

    public void commentDeleted() {
        this.isDeleted = true;
    }
}

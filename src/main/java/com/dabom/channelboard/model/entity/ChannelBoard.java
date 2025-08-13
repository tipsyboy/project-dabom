package com.dabom.channelboard.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String title;
    private String contents;
    private String createAt;
    @Setter
    private Boolean isDeleted;

    @Builder
    public ChannelBoard(Integer idx, String title, String contents, String createAt, String updateAt, Boolean isDeleted) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt;
        this.isDeleted = false;
    }

}

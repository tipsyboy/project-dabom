package com.dabom.boardcomment.model.entity;

import com.dabom.channelboard.model.entity.ChannelBoard;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String content;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "board_idx")
    private ChannelBoard channelBoard;

    @Builder
    public BoardComment(String content, ChannelBoard channelBoard) {  // board 매개변수 추가
        this.content = content;
        this.channelBoard = channelBoard;
        this.isDeleted = false;
    }

    public void delete() {
        this.isDeleted = true;
    }

}

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
    private ChannelBoard board;

    @Builder
    public BoardComment(String content, ChannelBoard board) {  // board 매개변수 추가
        this.content = content;
        this.board = board;
        this.isDeleted = false;
    }

    public void delete() {
        this.isDeleted = true;
    }

}

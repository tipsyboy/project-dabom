package com.dabom.channelboard.model.entity;

import com.dabom.boardcomment.model.entity.BoardComment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "channelBoard")
    private List<BoardComment> boardCommentList;


}

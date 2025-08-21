package com.dabom.channelboard.model.entity;

import com.dabom.boardcomment.model.entity.BoardComment;
import com.dabom.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.dabom.member.model.entity.Member;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String title;
    private String contents;
    @Setter
    private Boolean isDeleted;


    @Builder
    public ChannelBoard(Integer idx, String title, String contents) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
        this.isDeleted = false;
    }

    @OneToMany(mappedBy = "channelBoard")
    private List<BoardComment> boardCommentList;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member channel;

    public void updateContents(String contents) {
        this.contents = contents;
    }

}

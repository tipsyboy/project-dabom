package com.dabom.chat.model.entity;

import com.dabom.common.BaseEntity;
import com.dabom.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member1_idx", nullable = false)
    private Member member1;

    @ManyToOne
    @JoinColumn(name = "member2_idx", nullable = false)
    private Member member2;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public ChatRoom(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }

    public void deleteRoom() {
        this.isDeleted = true;
    }
}
package com.dabom.together.model.entity;

import com.dabom.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Together {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String title;
    private String videoUrl;
    private Integer maxMemberNum;
    private Integer joinMemberNum;
    private Boolean isOpen;
    private Boolean isDelete;
    private String invitedCode;

    @ManyToOne
    @JoinColumn(name = "master_idx")
    private Member master;

    @OneToMany(mappedBy = "together")
    private List<TogetherJoinMember> members;

    @Builder
    public Together(String title, String videoUrl, Integer maxMemberNum, Boolean isOpen, Boolean isDelete, Member master, String invitedCode) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.maxMemberNum = maxMemberNum;
        this.isOpen = isOpen;
        this.isDelete = isDelete;
        this.master = master;
        this.invitedCode = invitedCode;
        this.joinMemberNum = 1;
    }

    public void joinMember() {
        this.joinMemberNum++;
    }

    public void leaveMember() {
        this.joinMemberNum--;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeTogetherPrivate() {
        this.isOpen = false;
    }

    public void changeTogetherPublic() {
        this.isOpen = true;
    }

    public void changeMaxMemberNumber(int maxNumber) {
        this.maxMemberNum = maxNumber;
    }

    public void changeVideo(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void deleteTogether() {
        this.isDelete = true;
    }
}

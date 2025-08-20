package com.dabom.together.model.entity;

import com.dabom.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Boolean isOpen;
    private Boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "master_idx")
    private Member master;
    private String invitedCode;

    @Builder
    public Together(String title, String videoUrl, Integer maxMemberNum, Boolean isOpen, Boolean isDelete, Member master, String invitedCode) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.maxMemberNum = maxMemberNum;
        this.isOpen = isOpen;
        this.isDelete = isDelete;
        this.master = master;
        this.invitedCode = invitedCode;
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

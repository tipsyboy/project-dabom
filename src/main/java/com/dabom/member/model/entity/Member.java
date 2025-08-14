package com.dabom.member.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String email;
    private String name;
    private String password;
    private MemberRole memberRole;
//    private Channel channel;
//    private ImageFile profileImage;
    private Boolean isDeleted;

    @Builder
    public Member(String email, String name, String password, String memberRole) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.memberRole = MemberRole.valueOf(memberRole);
        this.isDeleted = false;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void deleteMember() {
        this.isDeleted = true;
    }
}

package com.dabom.together.repository;

import com.dabom.member.model.entity.Member;
import com.dabom.together.model.entity.Together;
import com.dabom.together.model.entity.TogetherJoinMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TogetherJoinMemberRepository extends JpaRepository<TogetherJoinMember, Integer> {
    Optional<TogetherJoinMember> findByMemberAndTogether(Member member, Together together);
}

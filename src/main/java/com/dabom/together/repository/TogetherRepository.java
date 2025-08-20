package com.dabom.together.repository;

import com.dabom.together.model.entity.Together;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TogetherRepository extends JpaRepository<Together, Integer> {
}

package com.dabom.image.repository;

import com.dabom.image.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Integer> {
}

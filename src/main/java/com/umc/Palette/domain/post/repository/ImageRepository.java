package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Long> {
}

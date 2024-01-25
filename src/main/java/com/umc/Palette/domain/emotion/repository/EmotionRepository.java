package com.umc.Palette.domain.emotion.repository;

import com.umc.Palette.domain.emotion.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}

package com.umc.Palette.domain.situation.repository;

import com.umc.Palette.domain.situation.domain.Situation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationRepository extends JpaRepository<Situation, Long> {
}

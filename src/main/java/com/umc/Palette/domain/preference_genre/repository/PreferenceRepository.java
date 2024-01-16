package com.umc.Palette.domain.preference_genre.repository;

import com.umc.Palette.domain.preference_genre.domain.PreferenceGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<PreferenceGenre, Long> {
}

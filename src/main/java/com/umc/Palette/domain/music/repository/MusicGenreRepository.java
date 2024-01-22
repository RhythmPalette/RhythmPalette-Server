package com.umc.Palette.domain.music.repository;

import com.umc.Palette.domain.music.domain.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicGenreRepository  extends JpaRepository<MusicGenre, Long> {
}

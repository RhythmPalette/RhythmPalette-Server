package com.umc.Palette.domain.music.repository;

import com.umc.Palette.domain.music.domain.MusicPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicPlaylistRepository extends JpaRepository<MusicPlaylist, Long> {
}

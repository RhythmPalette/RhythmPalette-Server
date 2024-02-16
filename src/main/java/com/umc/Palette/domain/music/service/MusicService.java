package com.umc.Palette.domain.music.service;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.music.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MusicService {
    private final MusicRepository musicRepository;

    @Transactional
    public Music findMusic(MusicRequest request) {
        Music music = musicRepository.findByTitleAndArtist(request.getTitle(), request.getArtist());

        if (music == null) {
            music = request.toEntity();
        }

        return music;
    }
}

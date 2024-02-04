package com.umc.Palette.domain.music.service;

import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.music.dto.request.PlaylistCreateRequest;
import com.umc.Palette.domain.music.dto.response.PlaylistCreateResponse;
import com.umc.Palette.domain.music.repository.PlaylistRepository;
import com.umc.Palette.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Transactional
    public PlaylistCreateResponse create(User user, PlaylistCreateRequest request) {
        Playlist playlist = request.toEntity(user);
        playlistRepository.save(playlist);
        return PlaylistCreateResponse.builder()
                .id(playlist.getId())
                .createdAt(playlist.getCreatedAt())
                .updatedAt(playlist.getUpdateAt())
                .build();
    }
}

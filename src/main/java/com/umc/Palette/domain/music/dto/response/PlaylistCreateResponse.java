package com.umc.Palette.domain.music.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlaylistCreateResponse {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public PlaylistCreateResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

package com.umc.Palette.domain.music.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaylistResponse {
    private final Long playlistId;
    private final List<PlaylistPostResponse> posts;

    @Builder
    public PlaylistResponse(Long playlistId, List<PlaylistPostResponse> posts) {
        this.playlistId = playlistId;
        this.posts = posts;
    }
}

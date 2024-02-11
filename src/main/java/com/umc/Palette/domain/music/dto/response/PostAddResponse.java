package com.umc.Palette.domain.music.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostAddResponse {
    private final Long playlistId;
    private final Long postId;

    @Builder
    public PostAddResponse(Long playlistId, Long postId) {
        this.playlistId = playlistId;
        this.postId = postId;
    }
}

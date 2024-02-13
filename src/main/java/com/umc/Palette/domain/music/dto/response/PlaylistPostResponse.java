package com.umc.Palette.domain.music.dto.response;

import com.umc.Palette.domain.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaylistPostResponse {
    private final Long postId;

    @Builder
    public PlaylistPostResponse(Long postId) {
        this.postId = postId;
    }

    public static PlaylistPostResponse from(Post post) {
        return PlaylistPostResponse.builder()
                .postId(post.getPostId())
                .build();
    }
}

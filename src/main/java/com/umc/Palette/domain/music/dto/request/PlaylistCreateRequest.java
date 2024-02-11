package com.umc.Palette.domain.music.dto.request;

import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.user.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistCreateRequest {
    @NotEmpty(message = "name is required")
    private String name;

    public Playlist toEntity(User user) {
        return Playlist.builder()
                .name(name)
                .user(user)
                .build();
    }
}

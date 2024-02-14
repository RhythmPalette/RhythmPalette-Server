package com.umc.Palette.domain.music.dto.request;

import com.umc.Palette.domain.music.domain.Music;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MusicRequest {
    @NotEmpty(message = "title is required")
    private String title;

    @NotEmpty(message = "artist is required")
    private String artist;

    @NotEmpty(message = "genre is required")
    private String genre;

    @NotEmpty(message = "imageUrl is required")
    private String imageUrl;

    @NotEmpty(message = "previewUrl is required")
    private String previewUrl;

    public Music toEntity() {
        return Music.builder()
                .title(title)
                .artist(artist)
                .genre(genre)
                .imageUrl(imageUrl)
                .previewUrl(previewUrl)
                .build();
    }
}

package com.umc.Palette.domain.music.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "musics")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "genre", nullable = true)
    private String genre;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "preview_url", nullable = true)
    private String previewUrl;

    @Builder
    public Music(String title, String artist, String genre, String imageUrl, String previewUrl) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
    }
}

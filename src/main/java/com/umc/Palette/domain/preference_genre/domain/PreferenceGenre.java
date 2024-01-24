package com.umc.Palette.domain.preference_genre.domain;

import com.umc.Palette.domain.music.domain.Genre;
import com.umc.Palette.domain.user.domain.User;
import jakarta.persistence.*;

@Entity
public class PreferenceGenre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_genre_id")
    private Long preferenceGenreId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public PreferenceGenre(){

    }
}

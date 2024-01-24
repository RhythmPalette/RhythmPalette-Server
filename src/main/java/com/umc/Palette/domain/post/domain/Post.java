package com.umc.Palette.domain.post.domain;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.user.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String content;

    @Column(name = "image", nullable = false)
    private String postImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "emotion_id")
//    private Emotion emotion;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "situation_id")
//    private Situation situation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}

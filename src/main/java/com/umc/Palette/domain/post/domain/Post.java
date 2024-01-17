package com.umc.Palette.domain.post.domain;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "music_id")
//    private Music music;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "emotion_id")
//    private Emotion emotion;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "situation_id")
//    private Situation situation;

}

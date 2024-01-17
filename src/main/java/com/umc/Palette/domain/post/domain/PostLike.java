package com.umc.Palette.domain.post.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_post_like")
public class PostLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_post_like_id")
    private Long postLikeId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}

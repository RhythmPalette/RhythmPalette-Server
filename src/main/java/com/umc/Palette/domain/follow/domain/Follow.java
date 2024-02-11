package com.umc.Palette.domain.follow.domain;

import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.user.domain.User;
import jakarta.persistence.*;

@Entity
public class Follow extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User followingId;


    public Follow(){}

}

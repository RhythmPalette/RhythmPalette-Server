package com.umc.Palette.domain.user.domain;

import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.follow.domain.Follow;
import com.umc.Palette.domain.preference_genre.domain.PreferenceGenre;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    private String introduction;

    @Column(length = 1, nullable = false)
    private Character gender;

    @Column(nullable = false)
    private LocalDate birth;

    @OneToMany(mappedBy = "followerId")
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "followingId")
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<PreferenceGenre> preferenceGenreList= new ArrayList<>();





    public User(){}

}

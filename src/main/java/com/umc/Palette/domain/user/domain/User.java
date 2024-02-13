package com.umc.Palette.domain.user.domain;


import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.domain.UserCommentLike;
import com.umc.Palette.domain.follow.domain.Follow;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.domain.PostLike;
import com.umc.Palette.domain.preference_genre.domain.PreferenceGenre;
import com.umc.Palette.domain.user.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends BaseTimeEntity implements UserDetails {

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

    @Column(nullable = false)    // 회원가입 후 입력 (필수)
    private String nickname;

    @Column(nullable = false)
    private String introduction;    // 회원가입 후 입력 (필수)

    @Column(length = 1, nullable = true)    // 선택 사항
    private Character gender;

    @Column(nullable = true)    // 선택사항
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Role role;    // 권한

    @Column(nullable = true)
    private String profileImg;

    @OneToMany(mappedBy = "followerId")
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "followingId")
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<PreferenceGenre> preferenceGenreList= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music musicId;

    @OneToMany(mappedBy = "user")
    private List<UserCommentLike> userCommentLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();


    public User(){}

    public void addPreferenceGenre(int preferenceGenre) {
        PreferenceGenre newPreference = new PreferenceGenre();
        newPreference.setUser_id(this);  // 현재 User 엔터티와 연결
        preferenceGenreList.add(newPreference);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

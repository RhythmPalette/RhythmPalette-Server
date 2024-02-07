package com.umc.Palette.domain.post.domain;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
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

    public void addPlaylist(Playlist playlist) {
        if (this.playlist != null) {
            this.playlist.getPosts().remove(this);
        }

        this.playlist = playlist;

        if (playlist != null) {
            playlist.getPosts().add(this);
        }
    }

    public void removePlaylist(Playlist playlist) {
        if (this.playlist != playlist) {
            throw new BaseException(BaseResponseStatus.POST_IS_NOT_ON_PLAYLIST);
        }
        else {
            this.playlist = null;
            playlist.getPosts().remove(this);
        }
    }
}

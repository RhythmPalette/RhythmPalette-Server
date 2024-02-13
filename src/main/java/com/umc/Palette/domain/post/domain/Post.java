package com.umc.Palette.domain.post.domain;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.situation.domain.Situation;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situation_id")
    private Situation situation;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;


    public void updateContent(String content){
        this.content = content;
    }
    @OneToMany(mappedBy = "post")
    private List<PostLike> postLike;

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
        }
    }
}

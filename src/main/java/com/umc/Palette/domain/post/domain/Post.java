package com.umc.Palette.domain.post.domain;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;



    @Column(name = "situation_1")
    private String situation1;
    @Column(name = "situation_2")
    private String situation2;
    @Column(name = "situation_3")
    private String situation3;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;


    public void updatePost(String content, String situation1, String situation2, String situation3){
        this.content = content;
        this.situation1 = situation1;
        this.situation2 = situation2;
        this.situation3 = situation3;
    }
    public void updateEmotion(Emotion emotion){
        this.emotion = emotion;
    }
    @OneToMany(mappedBy = "post")
    private List<PostLike> postLike = new ArrayList<>();

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

    public void setMusic(MusicRequest musicRequest){
        this.music = musicRequest.toEntity();
    }
}

package com.umc.Palette.domain.music.domain;

import com.umc.Palette.domain.base_time.BaseTimeEntity;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@SQLDelete(sql = "UPDATE playlists SET deleted = true WHERE playlist_id = ?")
@SQLRestriction("deleted = false")
@Getter
@NoArgsConstructor
public class Playlist extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist")
    private List<Post> posts = new ArrayList<>();

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Builder
    public Playlist(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void delete(boolean deleted) {
        this.deleted = deleted;
    }
}

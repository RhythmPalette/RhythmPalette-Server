package com.umc.Palette.domain.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_comment_likes")
@Getter
@NoArgsConstructor
public class UserCommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_comment_like_id")
    private Long id;


//    @Column(name = "user_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    @Column(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
}

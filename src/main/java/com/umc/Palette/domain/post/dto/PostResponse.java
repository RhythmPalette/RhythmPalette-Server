package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.situation.domain.Situation;
import com.umc.Palette.domain.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class postDetail {

        private Long postId;
        private String content;
        private User userInfo;
        //        private List<Comment> comments;
        private Emotion emotion;
        private Situation situation;
        private Music music;
        private Integer likeCount;
        private Boolean isLiked;
        private Long commentCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static postDetail of(Post post/*, User user*/){
//            boolean isLiked = post.getPostLike().contains(user);
            return postDetail.builder()
                    .postId(post.getPostId())
                    .content(post.getContent())
                    .userInfo(post.getUser())
//                    .comments(post.getComments)
                    .emotion(post.getEmotion())
                    .situation(post.getSituation())
                    .music(post.getMusic())
                    .likeCount(post.getPostLike().size())
//                    .commentCount(post.getComments.size())
//                    .isLiked(isLiked)
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdateAt())
                    .build();
        }
        public static List<postDetail> from(List<Post> posts/*, User user*/){
            return posts.stream().map(post ->postDetail.of(post/*,user*/)).collect(Collectors.toList());
        }
    }
}

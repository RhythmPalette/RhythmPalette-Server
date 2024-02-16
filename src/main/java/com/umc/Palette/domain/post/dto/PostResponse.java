package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.post.domain.Image;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import lombok.*;
import org.springframework.data.domain.Slice;

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
        private List<Comment> comments;
        private Emotion emotion;
        private String situation1;
        private String situation2;
        private String situation3;
        private Music music;
        private Integer likeCount;
        private Boolean isLiked = false;
        private Integer commentCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static postDetail of(Post post, User user){
            boolean isLiked = post.getPostLike().contains(user);
            return postDetail.builder()
                    .postId(post.getPostId())
                    .content(post.getContent())
                    .userInfo(post.getUser())
                    .comments(post.getComments() != null ? post.getComments() : null)
                    .emotion(post.getEmotion() != null ? post.getEmotion() : null)
                    .situation1(post.getSituation1())
                    .situation2(post.getSituation2())
                    .situation3(post.getSituation3())
                    .music(post.getMusic() != null ? post.getMusic() : null)
                    .likeCount(post.getPostLike() != null ? post.getPostLike().size() : null)
                    .commentCount(post.getComments()!= null ? post.getComments().size() : null)
                    .isLiked(isLiked)
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdateAt())
                    .build();
        }
        public static postDetail of(Post post){
            return postDetail.builder()
                    .postId(post.getPostId())
                    .content(post.getContent())
                    .userInfo(post.getUser())
                    .comments(post.getComments() != null ? post.getComments() : null)
                    .emotion(post.getEmotion() != null ? post.getEmotion() : null)
                    .situation1(post.getSituation1())
                    .situation2(post.getSituation2())
                    .situation3(post.getSituation3())
                    .music(post.getMusic() != null ? post.getMusic() : null)
                    .likeCount(post.getPostLike() != null ? post.getPostLike().size() : null)
                    .commentCount(post.getComments()!= null ? post.getComments().size() : null)
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdateAt())
                    .build();
        }
        public static List<postDetail> of(Slice<Post> posts, User user){
            return posts.stream().map(post ->postDetail.of(post,user)).collect(Collectors.toList());
        }
        public static List<postDetail> of(List<Post> posts, User user){
            return posts.stream().map(post ->postDetail.of(post,user)).collect(Collectors.toList());
        }
        public static List<postDetail> from(Slice<Post> posts){
            return posts.stream().map(post ->postDetail.of(post)).collect(Collectors.toList());
        }
    }

    @Getter
    public static class ImageDTO {
        private String id;
        private String model_version;
        private Image[] images;

        public static Image to(Image image){
            return Image.builder()
                    .id(image.getId())
                    .seed(image.getSeed())
                    .image(image.getImage())
                    .build();
        }
    }



}

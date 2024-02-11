package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.post.domain.Post;
import lombok.Getter;


public class PostRequest {

    @Getter
    public static class AddDTO {
        private String postImg;
        private String content;
        public Post toEntity(/*User user, Music music, Emotion emotion*/){
            return Post.builder()
//                    .user(user)
                    .postImg(postImg)
                    .content(content)
//                    .music(music)
//                    .emotion(emotion)
                    .build();
        }

    }

    @Getter
    public  static class UpdateDTO {
        private String content;
    }
}

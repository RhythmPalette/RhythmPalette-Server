package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;


public class PostRequestDTO {

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
}

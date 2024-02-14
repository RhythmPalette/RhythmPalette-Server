package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import lombok.Getter;


public class PostRequest {

    @Getter
    public static class AddDTO {
        private String postImg;
        private String content;
        private String situation1;
        private String situation2;
        private String situation3;
        private Long emotionId;
//        private MusicRequest musicRequest;
        public Post toEntity(User user, Emotion emotion){
            return Post.builder()
                    .user(user)
                    .postImg(postImg)
                    .content(content)
//                    .music(musicRequest.toEntity())
                    .situation1(situation1)
                    .situation2(situation2)
                    .situation3(situation3)
                    .emotion(emotion)
                    .build();
        }

    }

    @Getter
    public  static class UpdateDTO {
        private String content;
        private String situation1;
        private String situation2;
        private String situation3;
        private Long emotionId;
    }

    @Getter
    public static class ImageDTO {
        private String prompt;
        private Integer samples;
    }
}

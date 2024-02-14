package com.umc.Palette.domain.post.dto;

import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


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
    @Setter
    public static class ImageDTO {

        @Schema(description = "이미지 생성을 위한 영문")
        private String prompt;
        @Schema(description = "생성할 이미지의 개수. Service 코드에서 6개로 고정됨")
        private Integer samples;

    }
}

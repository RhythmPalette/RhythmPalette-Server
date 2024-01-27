package com.umc.Palette.domain.comment.dto;

import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CommentRequestDTO {

    @Getter
    public static class CreateDTO{
        private User userId;
        private Post postId;
        private String comment;
        private LocalDateTime createdAt;
    }


}
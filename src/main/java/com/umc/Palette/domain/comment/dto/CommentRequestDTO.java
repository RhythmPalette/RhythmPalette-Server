package com.umc.Palette.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CommentRequestDTO {

    @Getter
    public static class CreateDTO{
        private Long userId;
        private Long postId;
        private String comment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }


}

package com.umc.Palette.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class UserCommentLikeDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

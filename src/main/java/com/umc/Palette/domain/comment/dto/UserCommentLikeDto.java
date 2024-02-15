package com.umc.Palette.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserCommentLikeDto {
    private Long id;
    private String name;
}

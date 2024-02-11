package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;

import java.util.List;

public interface UserCommentLikeService {
    List<UserCommentLikeDto> commentLikes(Long postId, Long commentId);

    Boolean commentLikeAdd(Long postId, Long commentId);

    Boolean commentLikeCancel(Long postId, Long commentId);
}

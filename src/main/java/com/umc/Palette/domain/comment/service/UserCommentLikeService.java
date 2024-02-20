package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.user.domain.User;

import java.util.List;

public interface UserCommentLikeService {
    List<UserCommentLikeDto> commentLikes(Long commentId);

    Boolean commentLikeAdd(Long userId, Long commentId);

    Boolean commentLikeCancel(Long userId, Long commentId);
}

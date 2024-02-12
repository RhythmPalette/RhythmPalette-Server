package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.user.domain.User;

import java.util.List;

public interface UserCommentLikeService {
    List<UserCommentLikeDto> commentLikes(User user, Long commentId);

    Boolean commentLikeAdd(User user, Long commentId);

    Boolean commentLikeCancel(User user, Long commentId);
}

package com.umc.Palette.domain.comment.converter;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.domain.UserCommentLike;
import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.user.domain.User;

public class UserCommentLikeConverter {

    public static UserCommentLike toUserCommentLike(User user, Comment comment){
        UserCommentLike userCommentLike = UserCommentLike.builder().build();
        return UserCommentLike.builder()
                .user(user)
                .comment(comment)
                .build();
    }
    public static UserCommentLike toUserCommentLike(){
        UserCommentLike userCommentLike = UserCommentLike.builder().build();
        return UserCommentLike.builder()
                .build();
    }

    public static UserCommentLikeDto toUserCommentLikeDto(Long postId, Long commentId){
        UserCommentLikeDto userCommentLikeDto = UserCommentLikeDto.builder().build();
        return userCommentLikeDto;
    }

}

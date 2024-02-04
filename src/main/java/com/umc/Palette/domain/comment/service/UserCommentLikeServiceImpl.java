package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.converter.UserCommentLikeConverter;
import com.umc.Palette.domain.comment.domain.UserCommentLike;
import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.comment.repository.UserCommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommentLikeServiceImpl implements UserCommentLikeService {

    private UserCommentLikeRepository userCommentLikeRepository;

    @Override
    public List<UserCommentLikeDto> commentLikes(Long postId, Long commentId) {
        return null;
    }

    @Override
    public Boolean commentLikeAdd(Long postId, Long commentId) {
        UserCommentLike addUserCommentLike = UserCommentLikeConverter.toUserCommentLike(/*Long postId, Long commentId*/);
        userCommentLikeRepository.save(addUserCommentLike);
        return true;
    }

    @Override
    public Boolean commentLikeCancel(Long postId, Long commentId) {
        UserCommentLike cancelUserCommentLike = UserCommentLikeConverter.toUserCommentLike(/*Long postId, Long commentId*/);
        userCommentLikeRepository.delete(cancelUserCommentLike);
        return true;
    }
}

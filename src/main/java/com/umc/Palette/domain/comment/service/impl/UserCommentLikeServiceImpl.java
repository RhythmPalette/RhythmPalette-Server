package com.umc.Palette.domain.comment.service.impl;

import com.umc.Palette.domain.comment.converter.UserCommentLikeConverter;
import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.domain.UserCommentLike;
import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import com.umc.Palette.domain.comment.repository.UserCommentLikeRepository;
import com.umc.Palette.domain.comment.service.UserCommentLikeService;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommentLikeServiceImpl implements UserCommentLikeService {

    public final UserCommentLikeRepository userCommentLikeRepository;
    public final CommentRepository commentRepository;
    public final UserRepository userRepository;

    @Override
    public List<UserCommentLikeDto> commentLikes(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("comment not found"));
        List<UserCommentLikeDto> userCommentLikeDtoList=new ArrayList<>();
        for (UserCommentLike commentLike : comment.getLikes()) {
            userCommentLikeDtoList.add(
                    UserCommentLikeDto.builder()
                    .id(commentLike.getId())
                    .name(commentLike.getUser().getName())
                    .build()
            );
        }

        return userCommentLikeDtoList;
    }

    @Override
    public Boolean commentLikeAdd(Long userId, Long commentId) {
        Comment likedComment = commentRepository.findById(commentId).orElseThrow(()->new EntityNotFoundException("Comment not found"));
        User likedUser = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));
        Optional<UserCommentLike> checkUserCommentLike = Optional.ofNullable(userCommentLikeRepository.findByUserAndComment(likedUser, likedComment));
        if(checkUserCommentLike.isPresent()){
            return false;
        }

        UserCommentLike addUserCommentLike = UserCommentLikeConverter.toUserCommentLike(likedUser, likedComment);
        userCommentLikeRepository.save(addUserCommentLike);
        return true;
    }

    @Override
    public Boolean commentLikeCancel(Long userId, Long commentId) {
        Comment cancledComment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        User likedUser = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found"));
        Optional<UserCommentLike> cancelUserCommentLike = Optional.ofNullable(userCommentLikeRepository.findByUserAndComment(likedUser, cancledComment));
        if (cancelUserCommentLike.isPresent()) {
            userCommentLikeRepository.delete(cancelUserCommentLike.get());
            return true;
        } else {
            return false;
        }
    }
}

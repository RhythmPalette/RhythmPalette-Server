package com.umc.Palette.domain.comment.service.impl;

import com.umc.Palette.domain.comment.converter.CommentConverter;
import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import com.umc.Palette.domain.comment.service.CommentService;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentDto> getComments(Long postId) {
        log.info("before 스프링데이터jpa");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        log.info("after 스프링데이터jpa");
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : post.getComments()){
            log.info(comment.getId()+"");
            commentDtoList.add(
                    CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdateAt())
                    .likeCount(comment.getLikes().size())
                    .build()
            );
        }
        return commentDtoList;
    }

    @Override
    @Transactional
    public CommentDto createComment(CommentRequestDTO.CreateDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Comment newComment = CommentConverter.toComment(request, user, post);
//        Comment newComment = CommentConverter.toComment(request);

        Comment savedComment = commentRepository.save(newComment);
        CommentDto commentDto = CommentConverter.toCommentDto(savedComment);
        return commentDto;
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentRequestDTO.CreateDTO request) {
        Comment updateComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        updateComment.setContent(request.getComment());

        Comment savedComment = commentRepository.save(updateComment);
        CommentDto commentDto = CommentConverter.toCommentDto(savedComment);
        return commentDto;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return true;
    }
}

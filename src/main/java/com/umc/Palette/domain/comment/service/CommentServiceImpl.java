package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.converter.CommentConverter;
import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(CommentConverter::toCommentDto)
                .collect(Collectors.toList());


    }

    @Override
    @Transactional
    public CommentDto createComment(CommentRequestDTO.CreateDTO request) {
        Comment newComment = CommentConverter.toComment(request);

        Comment savedComment = commentRepository.save(newComment);
        return CommentConverter.toCommentDto(savedComment);
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentRequestDTO.CreateDTO request) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        Comment updatedComment = CommentConverter.toCommentWithId(commentId, request);


        Comment savedComment = commentRepository.save(updatedComment);
        return CommentConverter.toCommentDto(savedComment);
    }

    @Override
    public boolean deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return true;
    }
}

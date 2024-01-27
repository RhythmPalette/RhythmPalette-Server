package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getComments(Long postId) {
        return null;
    }

    @Override
    @Transactional
    public CommentDto createComment(CommentRequestDTO request) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentRequestDTO request) {
        return null;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        return false;
    }
}

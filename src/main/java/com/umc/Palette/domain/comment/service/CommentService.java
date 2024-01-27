package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;

import java.util.List;

public interface CommentService {

    public List<CommentDto> getComments(Long postId);

    public CommentDto createComment(CommentRequestDTO request);

    public CommentDto updateComment(Long commentId, CommentRequestDTO request);

    public boolean deleteComment(Long commentId);

}

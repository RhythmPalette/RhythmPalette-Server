package com.umc.Palette.domain.comment.service;

import com.umc.Palette.domain.comment.converter.CommentConverter;
import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.repository.CommentRepository;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

//    @Override
//    public List<CommentDto> getComments(Long postId) {
//        List<Comment> comments = commentRepository.findByPostId(postId);
//
//        return comments.stream()
//                .map(CommentConverter::toCommentDto)
//                .collect(Collectors.toList());
//
//
//    }

    @Override
    @Transactional
    public CommentDto createComment(CommentRequestDTO.CreateDTO request) {
        User user = new User();
        Post post = new Post();
        Comment newComment = CommentConverter.toComment(request, user, post);
        Comment savedComment = commentRepository.save(newComment);
        log.info(savedComment.getId()+"");
        log.info(savedComment.getContent());
        log.info(savedComment.getUser()+"");
        log.info(savedComment.getLikes()+"");
        log.info(savedComment.getPost()+"");
        log.info(savedComment.getCreatedAt()+"");
        log.info(savedComment.getUpdateAt()+"");
        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

        CommentDto commentDto = CommentConverter.toCommentDto(savedComment);
        log.info(commentDto.getId()+"");
        log.info(commentDto.getContent());
        log.info(commentDto.getLikeCount()+"");
        log.info(commentDto.getCreatedAt()+"");
        log.info(commentDto.getUpdatedAt()+"");
        return commentDto;
    }

//    @Override
//    public CommentDto updateComment(Long commentId, CommentRequestDTO.CreateDTO request) {
//        Comment existingComment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new RuntimeException("Comment not found"));
//        Comment updatedComment = CommentConverter.toCommentWithId(commentId, request);
//
//
//        Comment savedComment = commentRepository.save(updatedComment);
//        return CommentConverter.toCommentDto(savedComment);
//    }

//    @Override
//    public boolean deleteComment(Long commentId) {
//        commentRepository.deleteById(commentId);
//        return true;
//    }
}

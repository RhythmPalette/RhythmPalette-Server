package com.umc.Palette.domain.comment.converter;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;


public class CommentConverter {
    public static Comment toComment(CommentRequestDTO.CreateDTO createDTO){
        //id 어떻게할건지
        return Comment.builder()
                .content(createDTO.getComment())
                .user(createDTO.getUserId())
                .post(createDTO.getPostId())
                .createdAt(createDTO.getCreatedAt())
                .updatedAt(null).build();
    }

    public static Comment toComment(Long commentID, CommentRequestDTO.CreateDTO createDTO){
        //id 어떻게할건지
        return Comment.builder()
                .id(commentID)
                .content(createDTO.getComment())
                .user(createDTO.getUserId())
                .post(createDTO.getPostId())
                .createdAt(createDTO.getCreatedAt())
                .updatedAt(null).build();
    }



    public static CommentDto toCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .likeCount(comment.getLikes().size())
                .build();
    }
}

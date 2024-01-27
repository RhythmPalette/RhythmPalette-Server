package com.umc.Palette.domain.comment.converter;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;


public class CommentConverter {
    public static Comment toComment(CommentRequestDTO.CreateDTO createDTO){

        //id 어떻게할건지
        Comment newComment = Comment.builder()
                .content(createDTO.getComment())
                .user(createDTO.getUserId())
                .post(createDTO.getPostId())
                .createdAt(createDTO.getCreatedAt())
                .updatedAt(null).build();
        return  newComment;

    }
}

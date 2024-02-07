package com.umc.Palette.domain.comment.converter;

import com.umc.Palette.domain.comment.domain.Comment;
import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;

import java.util.ArrayList;


public class CommentConverter {
    public static Comment toComment(CommentRequestDTO.CreateDTO createDTO, User user, Post post){
        //id 어떻게할건지
        return Comment.builder()
                .content(createDTO.getComment())
                .user(user)
                .post(post)
                .likes(new ArrayList<>())
                .build();
    }
    //테스트용
    public static Comment toComment(CommentRequestDTO.CreateDTO createDTO){
        return Comment.builder()
                .content(createDTO.getComment())
                .likes(new ArrayList<>())
                .build();
    }


    public static Comment toCommentWithId(Long commentId, CommentRequestDTO.CreateDTO createDTO){
        return Comment.builder().build();
    }



    public static CommentDto toCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .likeCount(comment.getLikes().size())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdateAt())
                .build();
    }
}

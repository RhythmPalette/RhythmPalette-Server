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

//    public static Comment toComment(Long commentID, CommentRequestDTO.CreateDTO createDTO){
//        //id 어떻게할건지
//        return Comment.builder()
//                .id(commentID)
//                .content(createDTO.getComment())
//                .user(createDTO.getUserId())
//                .post(createDTO.getPostId())
//                .createdAt(createDTO.getCreatedAt())
//                .updatedAt(null).build();
//    }
//


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

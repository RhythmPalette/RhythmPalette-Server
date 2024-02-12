package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.comment.service.UserCommentLikeService;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts/{postId}/comments/{commentId}")
public class CommentLikeRestController {

    private final UserCommentLikeService userCommentLikeService;
    @GetMapping("/liked-people")
    public BaseResponse<List<UserCommentLikeDto>> getCommentLike(/*@유저*/ User user, @PathVariable(name = "commentId") Long commentId){
        List<UserCommentLikeDto> users = userCommentLikeService.commentLikes(user, commentId);
        return BaseResponse.<List<UserCommentLikeDto>>builder()
                .data(users)
                .isSuccess(true)
                .code(200)
                .message("조회를 성공하였습니다")
                .build();
    }

    @PostMapping("/likes")
    public BaseResponse<Boolean> postCommentLike(/*@유저*/ User user, @PathVariable(name = "commentId") Long commentId){

        Boolean commentLikeAdd = userCommentLikeService.commentLikeAdd(user, commentId);
        return BaseResponse.<Boolean>builder()
                .data(commentLikeAdd)
                .isSuccess(true)
                .code(200)
                .message("좋아요를 추가하였습니다.")
                .build();
    }

    @DeleteMapping("/likes")
    public BaseResponse<Boolean> deleteCommentLike(/*@유저*/ User user, @PathVariable(name = "commentId") Long commentId){

        Boolean commentLikeCancel = userCommentLikeService.commentLikeCancel(user, commentId);
        return BaseResponse.<Boolean>builder()
                .data(commentLikeCancel)
                .isSuccess(true)
                .code(200)
                .message("좋아요를 취소하였습니다.")
                .build();
    }
}

package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.domain.UserCommentLike;
import com.umc.Palette.domain.comment.dto.UserCommentLikeDto;
import com.umc.Palette.domain.comment.service.UserCommentLikeService;
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
    public BaseResponse<List<UserCommentLikeDto>> getCommentLike(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId){

        return BaseResponse.<List<UserCommentLikeDto>>builder()
                .isSuccess(true)
                .code(200)
                .message("조회를 성공하였습니다")
                .build();
    }

    @PostMapping("/likes")
    public BaseResponse<Boolean> postCommentLike(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId){

        Boolean commentLikeAdd = userCommentLikeService.commentLikeAdd(postId, commentId);
        return BaseResponse.<Boolean>builder()
                .data(commentLikeAdd)
                .isSuccess(true)
                .code(200)
                .message("좋아요를 추가하였습니다.")
                .build();
    }

    @DeleteMapping("/likes")
    public BaseResponse<Boolean> deleteCommentLike(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId){

        Boolean commentLikeCancel = userCommentLikeService.commentLikeCancel(postId, commentId);
        return BaseResponse.<Boolean>builder()
                .data(commentLikeCancel)
                .isSuccess(true)
                .code(200)
                .message("좋아요를 취소하였습니다.")
                .build();
    }
}

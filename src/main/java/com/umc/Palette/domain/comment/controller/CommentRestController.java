package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.service.CommentService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts/{postId}/comments")
public class CommentRestController {

    private final CommentService commentService;

//    @GetMapping
//    public ResponseEntity<List<CommentDto>> getComment(@PathVariable(name = "postId") Long postId) {
//        List<CommentDto> comments = commentService.getComments(postId);
//        return ResponseEntity.ok(comments);
//    }

    @PostMapping
    public BaseResponse<CommentDto> postComment(@RequestBody  CommentRequestDTO.CreateDTO request) {
        CommentDto createdComment = commentService.createComment(request);
        log.info("컨트롤러 return 확인");
        return BaseResponse.<CommentDto>builder()
                .data(createdComment)
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .build();
    }

    @PatchMapping("/{commentId}")
    public BaseResponse<CommentDto> patchComment(@PathVariable(name = "commentId") Long commentId, @RequestBody CommentRequestDTO.CreateDTO request) {
        CommentDto updatedComment = commentService.updateComment(commentId, request);
        return BaseResponse.<CommentDto>builder()
                .data(updatedComment)
                .code(200)
                .isSuccess(true)
                .message("게시글이 수정되었습니다.")
                .build();
    }

    @DeleteMapping("/{commentId}")
    public BaseResponse<Boolean> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        boolean isDeleted = commentService.deleteComment(commentId);
        return BaseResponse.<Boolean>builder()
                .data(isDeleted)
                .code(200)
                .isSuccess(true)
                .message("게시글이 삭제되었습니다.")
                .build();
    }
//
//    @GetMapping("/{commentId}/liked-people")
//    public void getCommentLike(){
//
//    }
//
//    @PostMapping("/{commentId}/likes")
//    public void postCommentLike(){
//
//    }
//
//    @DeleteMapping("/{commentId}/likes")
//    public void deleteCommentLike(){
//
//    }


}

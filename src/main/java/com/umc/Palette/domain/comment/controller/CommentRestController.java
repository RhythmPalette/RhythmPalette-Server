package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.service.CommentService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
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
        return BaseResponse.<CommentDto>builder()
                .data(createdComment)
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .build();
    }

//    @PatchMapping("/{commentId}")
//    public ResponseEntity<CommentDto> patchComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Validated CommentRequestDTO.CreateDTO request) {
//        CommentDto updatedComment = commentService.updateComment(commentId, request);
//        return ResponseEntity.ok(updatedComment);
//    }
//
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<Boolean> deleteComment(@PathVariable(name = "commentId") Long commentId) {
//        boolean isDeleted = commentService.deleteComment(commentId);
//        return ResponseEntity.ok(isDeleted);
//    }
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

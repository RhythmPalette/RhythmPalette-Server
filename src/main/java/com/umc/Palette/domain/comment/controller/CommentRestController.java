package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.service.CommentService;
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

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable(name = "postId") Long postId) {
        List<CommentDto> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDto> postComment(@RequestBody @Validated CommentRequestDTO.CreateDTO request) {
        CommentDto createdComment = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> patchComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Validated CommentRequestDTO.CreateDTO request) {
        CommentDto updatedComment = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        boolean isDeleted = commentService.deleteComment(commentId);
        return ResponseEntity.ok(isDeleted);
    }

    @GetMapping("/{commentId}/liked-people")
    public void getCommentLike(){

    }

    @PostMapping("/{commentId}/likes")
    public void postCommentLike(){

    }

    @DeleteMapping("/{commentId}/likes")
    public void deleteCommentLike(){

    }


}

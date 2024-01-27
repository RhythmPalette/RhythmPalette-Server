package com.umc.Palette.domain.comment.controller;

import com.umc.Palette.domain.comment.dto.CommentDto;
import com.umc.Palette.domain.comment.dto.CommentRequestDTO;
import com.umc.Palette.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
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
    public List<CommentDto> getComment(@PathVariable(name = "postId") Long postId){
        return commentService.getComments(postId);
    }

    @PostMapping
    public CommentDto postComment(@RequestBody @Validated CommentRequestDTO request){
        return commentService.createComment(request);
    }

    @PatchMapping("/{commentId}")
    public CommentDto patchComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Validated CommentRequestDTO request){
        return commentService.updateComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public boolean deleteComment(@PathVariable(name = "commentId") Long commentId){
        return commentService.deleteComment(commentId);
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

package com.umc.Palette.domain.post.controller;

import com.umc.Palette.domain.post.dto.PostRequestDTO;
import com.umc.Palette.domain.post.dto.PostResponseDTO;
import com.umc.Palette.domain.post.service.PostService;
import com.umc.Palette.global.exception.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public BaseResponse<PostResponseDTO> addPost(@RequestBody PostRequestDTO.AddDTO addDTO/*, HttpServletRequest request*/){
        postService.addPost(addDTO/*,request*/);
        return BaseResponse.<PostResponseDTO>builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .build();
    }

    @DeleteMapping("/{postId}")
    public BaseResponse<Object> deletePost(@PathVariable(name = "postId") Long postId){
        postService.deletePost(postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 삭제되었습니다.")
                .build();
    }
}

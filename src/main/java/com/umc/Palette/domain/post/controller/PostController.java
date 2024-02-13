package com.umc.Palette.domain.post.controller;

import com.umc.Palette.domain.post.domain.Image;
import com.umc.Palette.domain.post.dto.PostResponse;
import com.umc.Palette.domain.post.repository.ImageRepository;
import com.umc.Palette.domain.post.service.ImageService;
import com.umc.Palette.domain.post.dto.PostRequest;
import com.umc.Palette.domain.post.service.PostService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final ImageService imageService;

    @PostMapping
    public BaseResponse<Object> addPost(@RequestBody PostRequest.AddDTO addDTO/*, HttpServletRequest request*/){
        postService.addPost(addDTO/*,request*/);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .build();
    }



    @PatchMapping("/{postId}")
    public BaseResponse<Object> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostRequest.UpdateDTO updateDTO){
        postService.updatePost(updateDTO, postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 수정되었습니다.")

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

    @PostMapping("/image")
    public BaseResponse<Object> doInference(@RequestBody PostRequest.ImageDTO request) {

        PostResponse.ImageDTO response = imageService.createImage(request);

        return BaseResponse.builder()
                .code(200)
                .message("이미지가 생성되었습니다")
                .data(response)
                .build();
    }

}

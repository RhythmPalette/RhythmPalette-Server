package com.umc.Palette.domain.post.controller;

import com.umc.Palette.domain.post.domain.Image;
import com.umc.Palette.domain.post.repository.ImageRepository;
import com.umc.Palette.domain.post.service.ImageService;
import com.umc.Palette.domain.post.service.PostService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.umc.Palette.domain.post.dto.PostRequestDTO;
import com.umc.Palette.domain.post.dto.PostResponseDTO;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final ImageService imageService;

    @PostMapping
    public BaseResponse<Object> addPost(@RequestBody PostRequestDTO.AddDTO addDTO/*, HttpServletRequest request*/){
        postService.addPost(addDTO/*,request*/);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .build();
    }

    @GetMapping("/{postId}")
    public BaseResponse<Object> getPostDetail(@PathVariable(name = "postId") Long postId){
        PostResponseDTO.postDetail postDetail = postService.getPostDetail(postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetail)
                .message("게시글이 조회되었습니다.")
                .build();
    }

    @GetMapping
    public BaseResponse<Object> getPostList(){
        List<PostResponseDTO.postDetail> postDetailList = postService.getPostList();
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailList)
                .message("게시글 리스트가 조회되었습니다")
                .build();
    }

    @GetMapping("/calender/{month}")
    public BaseResponse<Object> getPostCalender(@PathVariable (name = "month") int month){
        List<PostResponseDTO.postDetail> postDetailListByMonth = postService.getPostCalendar(month);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailListByMonth)
                .message("게시글 캘린더가 조회되었습니다")
                .build();
    }

    @GetMapping("/mypage")
    public BaseResponse<Object> getPostMyPage(@RequestBody Long userId){
        List<PostResponseDTO.postDetail> postListMyPage = postService.getPostMyPage(userId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postListMyPage)
                .message("내 게시글이 조회되었습니다.")
                .build();
    }
    @PatchMapping("/{postId}")
    public BaseResponse<Object> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostRequestDTO.UpdateDTO updateDTO){
        postService.updatePost(updateDTO, postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 수정되었습니다.")
                .build();
    }
    @DeleteMapping("/{postId}")
    public BaseResponse<Object> deletePost(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 삭제되었습니다.")
                .build();
    }

    @PostMapping("/image")
    public BaseResponse<Object> doInference(@RequestBody PostRequestDTO.ImageDTO request) {

        PostResponseDTO.ImageDTO response = imageService.createImage(request);

        return BaseResponse.builder()
                .code(200)
                .message("이미지가 생성되었습니다")
                .data(response)
                .build();
    }
}

package com.umc.Palette.domain.post.controller;

import com.umc.Palette.domain.music.dto.request.MusicRequest;
import com.umc.Palette.domain.post.service.ImageService;
import com.umc.Palette.domain.post.service.PostService;
import com.umc.Palette.domain.post.service.PostLikeService;

import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.config.annotation.LoggedInUser;
import com.umc.Palette.global.exception.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import com.umc.Palette.domain.post.dto.PostRequest;
import com.umc.Palette.domain.post.dto.PostResponse;
import com.umc.Palette.domain.post.dto.SliceResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;
    private final ImageService imageService;
    private final PostLikeService postLikeService;

    @PostMapping
    public BaseResponse<Object> addPost(@RequestBody PostRequest.AddDTO addDTO, @LoggedInUser User user){
        PostResponse.postDetail post = postService.addPost(addDTO,user);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 작성되었습니다.")
                .data(post)
                .build();
    }

    @GetMapping("/{postId}")
    public BaseResponse<Object> getPostDetail(@PathVariable(name = "postId") Long postId, @LoggedInUser User user){
        PostResponse.postDetail postDetail = postService.getPostDetail(postId, user);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetail)
                .message("게시글이 조회되었습니다.")
                .build();
    }

    @GetMapping
    public BaseResponse<Object> getPostList(@LoggedInUser User user, @PageableDefault(size = 10) Pageable pageable){

        SliceResponse postDetailList;
        if(user.getFollowingList() == null || user == null){ //비로그인 유저 + 팔로잉이 없는 유저
            postDetailList = postService.getPostList(user, pageable);
        }
        else { //로그인 + 팔로잉이 존재하는 유저
            postDetailList = postService.getPostListWithFollow(user, pageable);
        }

        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailList)
                .message("게시글 리스트가 조회되었습니다")
                .build();
    }
    @GetMapping("/search/{genre}")
    public BaseResponse<Object> getPostListWithGenre(@PathVariable(name = "genre")String genre, @PageableDefault(size = 10) Pageable pageable, @LoggedInUser User user){
        SliceResponse postDetailList = postService.getPostListWithGenre(genre,user, pageable);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailList)
                .message("장르별 게시글 리스트가 조회되었습니다")
                .build();
    }
    @GetMapping("/search/{emotionId}")
    public BaseResponse<Object> getPostListWithEmotion(@PathVariable(name = "emotionId")Long emotionId, @PageableDefault(size = 10) Pageable pageable, @LoggedInUser User user){
        SliceResponse postDetailList = postService.getPostListWithEmotion(emotionId,user, pageable);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailList)
                .message("감정별 게시글 리스트가 조회되었습니다")
                .build();
    }



    @GetMapping("/calender/{month}")
    public BaseResponse<Object> getPostCalender(@PathVariable (name = "month") int month,@LoggedInUser User user){
        List<PostResponse.postDetail> postDetailListByMonth = postService.getPostCalendar(month, user);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postDetailListByMonth)
                .message("게시글 캘린더가 조회되었습니다")
                .build();
    }

    @GetMapping("/mypage")
    public BaseResponse<Object> getPostMyPage(@LoggedInUser User user, @PageableDefault(size = 10) Pageable pageable){
        SliceResponse postListMyPage = postService.getPostMyPage(user, pageable);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(postListMyPage)
                .message("내 게시글이 조회되었습니다.")
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
    public BaseResponse<Object> deletePost(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("게시글이 삭제되었습니다.")
                .build();
    }

    @Operation(description = "이미지를 6개 생성해 https://mk.kakaocdn.net/dna/karlo/image/~ 와 같은 url형식으로 반환됨")
    @PostMapping("/image")
    public BaseResponse<Object> createImage(@RequestBody PostRequest.ImageDTO request) {

        PostResponse.ImageDTO response = imageService.createImage(request);

        return BaseResponse.builder()
                .code(200)
                .message("이미지가 생성되었습니다")
                .data(response)
                .build();
    }

    @PostMapping("/{postId}/like")
    public BaseResponse<Object> addPostLike(@PathVariable(name = "postId")Long postId, @LoggedInUser User user){
        postLikeService.addPostLike(postId,user);
        return BaseResponse.builder()
                .code(200)
                .message("게시글에 좋아요를 누르셨습니다.")
                .build();
    }
    @DeleteMapping("/{postId}/like")
    public BaseResponse<Object> deletePostLike(@PathVariable(name = "postId")Long postId, @LoggedInUser User user){
        postLikeService.deletePostLike(postId,user);
        return BaseResponse.builder()
                .code(200)
                .message("게시글에 좋아요를 취소하셨습니다.")
                .build();
    }

    @Operation(description = "사용자가 선택한 이미지를 url로 입력 받아 서버에 저장 후 해당 이미지의 S3 url을 반환")
    @PostMapping("/image/upload")
    public BaseResponse<Object> uploadImageToServer(@RequestParam(name = "imageUrl") String imageUrl) throws IOException {
        String image = imageService.uploadImageFromUrl(imageUrl);
        return BaseResponse.builder()
                .code(200)
                .message("이미지가 업로드 되었습니다")
                .data(image)
                .build();
    }

}

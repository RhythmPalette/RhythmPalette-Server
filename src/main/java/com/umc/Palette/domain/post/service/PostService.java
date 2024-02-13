package com.umc.Palette.domain.post.service;

import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.dto.PostRequest;
import com.umc.Palette.domain.post.dto.PostResponse;
import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.global.exception.BaseResponse;
import com.umc.Palette.global.exception.CustomExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.umc.Palette.global.exception.BaseResponseStatus.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    public void addPost(PostRequest.AddDTO addDTO/*, HttpServletRequest request*/){
//        User user = userRepository.findById(jwtTokenProvider.getCurrentUser(request)).orElseThrow(
//                ()->new CustomExceptionHandler(USER_NOT_FOUND));
//        Music music = musicRepository.findById(addDTO.getMusicId()).orElseThrow(
//                ()->new CustomExceptionHandler(ENTITY_NOT_FOUND));
        postRepository.save(addDTO.toEntity(/*user, music, emotion*/));
    }

    public PostResponse.postDetail getPostDetail(Long postId /*, HttpServletRequest request*/){
        Post post = postRepository.findById(postId).orElseThrow();
//        User user = userRepository.findById().orElseThrow();

        return PostResponse.postDetail.of(post/*, user*/);
    }

    public List<PostResponse.postDetail> getPostList(){
        List<Post> postList = postRepository.findAll();
        return PostResponse.postDetail.from(postList);
    }

    public List<PostResponse.postDetail> getPostCalendar(int month){
        List<Post> postListByMonth = postRepository.findAllByMonth(month);
        return PostResponse.postDetail.from(postListByMonth);
    }

    public List<PostResponse.postDetail> getPostMyPage(Long userId){
        List<Post> postListMyPage = postRepository.findByUser_UserIdOrderByCreatedAtDesc(userId);
        return PostResponse.postDetail.from(postListMyPage);
    }
    public void updatePost(PostRequest.UpdateDTO updateDTO, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        post.updateContent(updateDTO.getContent());
        postRepository.save(post);

    }
    public void deletePost(Long postId){
            postRepository.deleteById(postId);
    }

}

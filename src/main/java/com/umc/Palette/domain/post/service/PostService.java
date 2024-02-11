package com.umc.Palette.domain.post.service;


import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.dto.PostRequestDTO;
import com.umc.Palette.domain.post.dto.PostResponseDTO;
import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
//    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    public void addPost(PostRequestDTO.AddDTO addDTO/*, HttpServletRequest request*/){
//        User user = userRepository.findById(jwtTokenProvider.getCurrentUser(request)).orElseThrow(
//                ()->new CustomExceptionHandler(USER_NOT_FOUND));
//        Music music = musicRepository.findById(addDTO.getMusicId()).orElseThrow(
//                ()->new CustomExceptionHandler(ENTITY_NOT_FOUND));
        postRepository.save(addDTO.toEntity(/*user, music, emotion*/));
    }

    public PostResponseDTO.postDetail getPostDetail(Long postId /*, HttpServletRequest request*/){
        Post post = postRepository.findById(postId).orElseThrow();
//        User user = userRepository.findById().orElseThrow();

        return PostResponseDTO.postDetail.of(post/*, user*/);
    }

    public List<PostResponseDTO.postDetail> getPostList(){
        List<Post> postList = postRepository.findAll();
        return PostResponseDTO.postDetail.from(postList);
    }

    public List<PostResponseDTO.postDetail> getPostCalendar(int month){
        List<Post> postListByMonth = postRepository.findAllByMonth(month);
        return PostResponseDTO.postDetail.from(postListByMonth);
    }

    public List<PostResponseDTO.postDetail> getPostMyPage(Long userId){
        List<Post> postListMyPage = postRepository.findByUser_UserIdOrderByCreatedAtDesc(userId);
        return PostResponseDTO.postDetail.from(postListMyPage);
    }
    public void updatePost(PostRequestDTO.UpdateDTO updateDTO, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        post.updateContent(updateDTO.getContent());
        postRepository.save(post);

    }
    public void deletePost(Long postId){
            postRepository.deleteById(postId);
    }

}

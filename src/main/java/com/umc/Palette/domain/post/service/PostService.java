package com.umc.Palette.domain.post.service;
import com.umc.Palette.domain.emotion.domain.Emotion;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.post.dto.PostRequestDTO;
import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.global.exception.CustomExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.umc.Palette.global.exception.BaseResponseStatus.ENTITY_NOT_FOUND;
import static com.umc.Palette.global.exception.BaseResponseStatus.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    public void addPost(PostRequestDTO.AddDTO addDTO/*, HttpServletRequest request*/){
//        User user = userRepository.findById(jwtTokenProvider.getCurrentUser(request)).orElseThrow(
//                ()->new CustomExceptionHandler(USER_NOT_FOUND));
//        Music music = musicRepository.findById(addDTO.getMusicId()).orElseThrow(
//                ()->new CustomExceptionHandler(ENTITY_NOT_FOUND));
        postRepository.save(addDTO.toEntity(/*user, music, emotion*/));
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}

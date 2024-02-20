package com.umc.Palette.domain.post.service;

import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.domain.PostLike;

import com.umc.Palette.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.umc.Palette.domain.post.repository.PostLikeRepository;
import com.umc.Palette.domain.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public void addPostLike(Long postId, Long userId){
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        postLikeRepository.save(new PostLike(post,user));
    }

    public void deletePostLike(Long postId, Long userId){
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId, userId);
        postLikeRepository.delete(postLike);
    }
}

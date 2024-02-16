package com.umc.Palette.domain.post.service;

import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.domain.PostLike;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.umc.Palette.domain.post.repository.PostLikeRepository;
import com.umc.Palette.domain.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    public void addPostLike(Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow();
        postLikeRepository.save(new PostLike(post,user));
    }

    public void deletePostLike(Long postId, User user){
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId, user.getUserId());
        postLikeRepository.delete(postLike);
    }
}

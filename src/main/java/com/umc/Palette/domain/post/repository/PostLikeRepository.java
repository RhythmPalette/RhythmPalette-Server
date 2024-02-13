package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

//    PostLike findByPostIdAndUserId(Long postId, Long userId);
}

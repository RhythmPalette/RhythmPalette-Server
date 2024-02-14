package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @Query("SELECT pl from PostLike pl where pl.post.postId =:postId and pl.user.userId =:userId")
    PostLike findByPostIdAndUserId(@Param("postId")Long postId,@Param("userId")Long userId);
}

package com.umc.Palette.domain.comment.repository;

import com.umc.Palette.domain.comment.domain.UserCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentLikeRepository extends JpaRepository<UserCommentLike, Long> {
}

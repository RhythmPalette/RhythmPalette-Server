package com.umc.Palette.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCommentLikeRepository extends JpaRepository<UserCommentLikeRepository, Long> {
}

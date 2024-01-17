package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

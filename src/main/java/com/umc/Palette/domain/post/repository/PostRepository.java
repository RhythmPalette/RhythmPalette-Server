package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    void deleteById(Long id);
    Optional<Post> findById(Long id);
}

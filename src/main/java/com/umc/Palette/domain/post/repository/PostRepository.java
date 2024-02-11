package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;



public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);
    void deleteById(Long id);

    @Query("SELECT p FROM Post p WHERE MONTH(p.createdAt) = :month")
    List<Post> findAllByMonth(@Param("month") int month);

    List<Post> findByUser_UserIdOrderByCreatedAtDesc(Long userId);

}

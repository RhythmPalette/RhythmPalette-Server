package com.umc.Palette.domain.post.repository;

import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    @Query("SELECT p FROM Post p WHERE MONTH(p.createdAt) = :month")
    List<Post> findAllByMonth(@Param("month") int month);

    @Query("select p from Post p where p.user = :user order by p.createdAt desc ")
    Slice<Post> findPostByUserOrderByCreatedAtDesc(@Param("user")User user, Pageable pageable);

    Slice<Post> findAllBy(Pageable pageable);

    @Query("select p from Post p where p.music.genre =:genre")
    Slice<Post> findAllPostByGenre(@Param("genre") String genre, Pageable pageable);

    @Query("select p from Post p where p.emotion.emotionId =:emotionId")
    Slice<Post> findAllPostByEmotion(@Param("emotionId") Long emotionId, Pageable pageable);

    @Query("select p from Post p " +
            "where p.user.userId in " +
            "(select f.followingId.userId from Follow f " +
            "where f.followerId.userId = :userId) " +
            "order by p.createdAt desc")
    Slice<Post> findAllPostByFollow(@Param("userId") Long userId, Pageable pageable);


    @Query("select p from Post p where p.user.userId =:userId order by p.createdAt desc ")
    List<Post> findAllByUserId(@Param("userId")Long userId);
}

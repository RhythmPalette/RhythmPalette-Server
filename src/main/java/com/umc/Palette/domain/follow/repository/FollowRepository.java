package com.umc.Palette.domain.follow.repository;

import com.umc.Palette.domain.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

//    public Optional<User>;
    boolean existsByFollowerIdUserIdAndFollowingIdUserId(Long followerId_userId, Long followingId_userId);

    Follow findByFollowerIdUserIdAndFollowingIdUserId(Long userId, Long followingId);


    // userId로 유저를 조회하고, 해당 유저와 followingId 간의 관계가 있는지 확인

}




package com.umc.Palette.domain.follow.service;


import com.umc.Palette.global.exception.BaseException;

public interface FollowService {
    Boolean createFollowing(Long userId,Long followingId) throws BaseException;

    void deleteFollowing(Long userId, Long followingId);

    void deleteFollower(Long userId, Long followerId);
}
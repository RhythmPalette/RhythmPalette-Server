package com.umc.Palette.domain.follow.service;


import com.umc.Palette.global.exception.BaseException;

public interface FollowService {
    Boolean createFollow(Long userId,Long followingId) throws BaseException;

}
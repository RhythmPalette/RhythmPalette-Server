package com.umc.Palette.domain.follow.service;

import com.umc.Palette.domain.follow.domain.Follow;
import com.umc.Palette.domain.follow.repository.FollowRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;

    public Boolean createFollow(Long userId, Long followingId) throws BaseException {
        if (followRepository.existsByFollowerIdUserIdAndFollowingIdUserId(userId, followingId)) {
            throw new BaseException(BaseResponseStatus.ALREADY_FOLLOW);
        } else{
            User followerUser = new User();
            followerUser.setUserId(userId);

            User followingUser = new User();
            followingUser.setUserId(followingId);

            Follow follow = new Follow();
            follow.setFollowerId(followerUser);
            follow.setFollowingId(followingUser);
            followRepository.save(follow);
        }
        return true;
    }

}
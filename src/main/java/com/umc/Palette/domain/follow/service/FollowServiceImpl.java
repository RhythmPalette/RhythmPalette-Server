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

    public Boolean createFollowing(Long userId, Long followingId) throws BaseException {
        if (followRepository.existsByFollowerIdUserIdAndFollowingIdUserId(userId, followingId)) {
            throw new BaseException(BaseResponseStatus.ALREADY_FOLLOWING);
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

    @Override
    public void deleteFollowing(Long userId, Long followingId) {

        Follow follow = followRepository.findByFollowerIdUserIdAndFollowingIdUserId(userId, followingId);
        if (follow != null) {
            followRepository.delete(follow);
        } else {
            throw new BaseException(BaseResponseStatus.NOT_FOLLOWING);
        }
    }

    @Override
    public void deleteFollower(Long userId, Long followerId) {
        Follow follow = followRepository.findByFollowerIdUserIdAndFollowingIdUserId(followerId, userId);
        if (follow != null) {
            followRepository.delete(follow);
        } else {
            throw new BaseException(BaseResponseStatus.NOT_FOLLOWER);
        }




    }
}
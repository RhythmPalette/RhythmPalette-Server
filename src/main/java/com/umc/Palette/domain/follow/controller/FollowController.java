package com.umc.Palette.domain.follow.controller;


import com.umc.Palette.domain.follow.service.FollowService;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;


    //팔로우 신청
    @ResponseBody
    @PostMapping("/{userId}/{followingId}")
    public BaseResponse<Object> followUser(@PathVariable("userId") Long userId, @PathVariable("followingId") Long followingId) throws BaseException {
        followService.createFollow(userId, followingId);
        return BaseResponse.<Object>builder()
                .code(3000)
                .isSuccess(true)
                .message("팔로우가 완료되었습니다.")
                .build();
    }
}
package com.umc.Palette.domain.user.controller;

import com.umc.Palette.domain.user.service.UserService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/{userName}")
    public BaseResponse<Object> hi(@PathVariable(name = "userName") String userName) {
        return BaseResponse.<Object>builder()
                .code(2005)
                .isSuccess(true)
                .message(userName+"안녕하세요")
                .build();
    }

}

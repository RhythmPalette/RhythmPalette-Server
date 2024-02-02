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
        return new BaseResponse<>(true, "안녕하세요", 2005, userName);
    }

}

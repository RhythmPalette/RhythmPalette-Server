package com.umc.Palette.domain.user.controller;

import com.umc.Palette.domain.post.service.ImageService;
import com.umc.Palette.domain.user.dto.PasswordCheckRequest;
import com.umc.Palette.domain.user.dto.ProfileRequest;
import com.umc.Palette.domain.user.service.FileUploadService;
import com.umc.Palette.domain.user.service.UserService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileUploadService fileUploadService;

    @GetMapping("/{userName}")
    public BaseResponse<Object> hi(@PathVariable(name = "userName") String userName) {
        return BaseResponse.<Object>builder()
                .code(2005)
                .isSuccess(true)
                .message(userName + "안녕하세요")
                .build();
    }

    @PostMapping("/profile/{loginId}")
    public BaseResponse<Object> profileSave(@RequestBody ProfileRequest profileRequest, @PathVariable("loginId")String loginId) {
        userService.profileSave(profileRequest, loginId);
        return BaseResponse.<Object>builder()
                .code(4000)
                .isSuccess(true)
                .message("프로필 등록 성공")
                .build();
    }

    @PostMapping("/profile/image/upload")
    public BaseResponse<Object> uploadFile(@RequestPart("file")MultipartFile file) throws IOException {
        String url = fileUploadService.uploadFile(file);
        return BaseResponse.<Object>builder()
                .code(4001)
                .message("이미지가 업로드 되었습니다")
                .data(url)
                .build();
    }

}

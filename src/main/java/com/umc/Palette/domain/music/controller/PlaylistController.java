package com.umc.Palette.domain.music.controller;

import com.umc.Palette.domain.music.dto.request.PlaylistCreateRequest;
import com.umc.Palette.domain.music.dto.response.PlaylistCreateResponse;
import com.umc.Palette.domain.music.service.PlaylistService;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.config.annotation.LoggedInUser;
import com.umc.Palette.global.exception.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    BaseResponse<PlaylistCreateResponse> create(
            @LoggedInUser User user,
            @Valid @RequestBody PlaylistCreateRequest request
    ) {
        PlaylistCreateResponse response = playlistService.create(user, request);
        return BaseResponse.<PlaylistCreateResponse>builder()
                .isSuccess(true)
                .message("플레이리스트가 정상적으로 생성되었습니다.")
                .code(201)
                .data(response)
                .build();
    }
}

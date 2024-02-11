package com.umc.Palette.domain.music.controller;

import com.umc.Palette.domain.music.dto.request.PlaylistCreateRequest;
import com.umc.Palette.domain.music.dto.response.PlaylistResponse;
import com.umc.Palette.domain.music.dto.response.PostAddResponse;
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

    // 플레이리스트 추가
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

    // 플레이리스트에 게시글 추가
    @PatchMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    BaseResponse<PostAddResponse> addPost(
            @PathVariable("playlistId") Long playlistId,
            @RequestParam("postId") Long postId
    ) {
        PostAddResponse response = playlistService.addPost(playlistId, postId);
        return BaseResponse.<PostAddResponse>builder()
                .isSuccess(true)
                .message("플레이리스트에 정상적으로 해당 게시물이 추가되었습니다.")
                .code(200)
                .data(response)
                .build();
    }

    // 플레이리스트에서 게시글 삭제
    @PatchMapping("/{playlistId}/delete")
    @ResponseStatus(HttpStatus.OK)
    BaseResponse removePost(
            @PathVariable("playlistId") Long playlistId,
            @RequestParam("postId") Long postId
    ) {
        playlistService.removePost(playlistId, postId);
        return BaseResponse.builder()
                .isSuccess(true)
                .message("플레이리스트에서 정상적으로 해당 게시물이 삭제되었습니다.")
                .code(200)
                .data(null)
                .build();
    }

    // 플레이리스트 조회
    @GetMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    BaseResponse<PlaylistResponse> getPlaylist(
            @PathVariable("playlistId") Long playlistId
    ) {
        PlaylistResponse response = playlistService.getPlaylist(playlistId);
        return BaseResponse.<PlaylistResponse>builder()
                        .isSuccess(true)
                        .message("해당 플레이리스트의 게시물들이 정상적으로 조회되었습니다.")
                        .code(200)
                        .data(response)
                        .build();
    }

    // 플레이리스트 삭제
    @DeleteMapping("/{playlistId}")
    @ResponseStatus(HttpStatus.OK)
    BaseResponse deletePlaylist(
            @PathVariable("playlistId") Long playlistId
    ) {
        playlistService.deletePlaylist(playlistId);
        return BaseResponse.builder()
                .isSuccess(true)
                .message("해당 플레이리스트가 정상적으로 삭제되었습니다.")
                .code(200)
                .data(null)
                .build();
    }
}

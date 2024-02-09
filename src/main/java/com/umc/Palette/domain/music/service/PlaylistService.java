package com.umc.Palette.domain.music.service;

import com.umc.Palette.domain.music.domain.Playlist;
import com.umc.Palette.domain.music.dto.request.PlaylistCreateRequest;
import com.umc.Palette.domain.music.dto.response.PlaylistPostResponse;
import com.umc.Palette.domain.music.dto.response.PlaylistResponse;
import com.umc.Palette.domain.music.dto.response.PostAddResponse;
import com.umc.Palette.domain.music.dto.response.PlaylistCreateResponse;
import com.umc.Palette.domain.music.repository.PlaylistRepository;
import com.umc.Palette.domain.post.domain.Post;
import com.umc.Palette.domain.post.repository.PostRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PostRepository postRepository;


    @Transactional
    public PlaylistCreateResponse create(User user, PlaylistCreateRequest request) {
        Playlist playlist = request.toEntity(user);
        playlistRepository.save(playlist);
        return PlaylistCreateResponse.builder()
                .id(playlist.getId())
                .createdAt(playlist.getCreatedAt())
                .updatedAt(playlist.getUpdateAt())
                .build();
    }

    @Transactional
    public PostAddResponse addPost(Long playlistId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseResponseStatus.POST_NOT_FOUND));
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new BaseException(BaseResponseStatus.PLAYLIST_NOT_FOUND));
        post.addPlaylist(playlist);
        return PostAddResponse.builder()
                .playlistId(playlist.getId())
                .postId(post.getPostId())
                .build();
    }

    @Transactional
    public void removePost(Long playlistId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(BaseResponseStatus.POST_NOT_FOUND));
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new BaseException(BaseResponseStatus.PLAYLIST_NOT_FOUND));
        post.removePlaylist(playlist);
    }

    @Transactional
    public PlaylistResponse getPlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new BaseException(BaseResponseStatus.PLAYLIST_NOT_FOUND));
        List<PlaylistPostResponse> posts = playlist.getPosts().stream().map(PlaylistPostResponse::from).toList();
        return PlaylistResponse.builder()
                .playlistId(playlistId)
                .posts(posts)
                .build();
    }

    @Transactional
    public void deletePlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new BaseException(BaseResponseStatus.PLAYLIST_NOT_FOUND));
        Iterator<Post> iterator = playlist.getPosts().iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            post.removePlaylist(playlist);
            iterator.remove();
        }
    }
}

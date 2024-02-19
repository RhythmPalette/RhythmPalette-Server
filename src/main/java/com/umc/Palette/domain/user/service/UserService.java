package com.umc.Palette.domain.user.service;
import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.ProfileRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService {

    UserDetailsService userDetailsService();


    User profileSave(ProfileRequest profileRequest, String loginId);


    Map<String, Object> getProfileInfo(String loginId);

    User profileMusicSave(Music music, String loginId);

    Map<String, Object> visit(String visitedUserId);

    User saveUploadImg(String file, String loginId);
}


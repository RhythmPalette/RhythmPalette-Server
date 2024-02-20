package com.umc.Palette.domain.user.service.impl;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.ProfileRequest;
import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByLoginId(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User saveUploadImg(String url, String loginId) {
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setProfileImg(url);

            return userRepository.save(user);
        } else {
            return null;
        }

    }

    @Override
    public User profileSave(ProfileRequest profileRequest, String loginId) {

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setNickname(profileRequest.getNickname());
            user.setIntroduction(profileRequest.getIntroduction());
            user.setBirth(LocalDate.parse(profileRequest.getBirth()));
            user.setGender(profileRequest.getGender());

            user.addPreferenceGenre(profileRequest.getPreferenceGenre1());
            user.addPreferenceGenre(profileRequest.getPreferenceGenre2());
            user.addPreferenceGenre(profileRequest.getPreferenceGenre3());

            return userRepository.save(user);

        }
        return null;
    }

    @Override
    public Map<String, Object> getProfileInfo(String loginId) {
        return getStringObjectMap(loginId);
    }

    @Override
    public User profileMusicSave(Music music , String loginId) {

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setMusicId(music);

            System.out.println(music.getTitle());


            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> visit(String visitedUserId) {

        return getStringObjectMap(visitedUserId);
    }

    private Map<String, Object> getStringObjectMap(String visitedUserId) {
        Optional<User> optionalUser = userRepository.findByLoginId(visitedUserId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Map<String, Object> profileInfo = new HashMap<>();
            profileInfo.put("userId", user.getUserId());
            profileInfo.put("loginId", user.getLoginId());
//            profileInfo.put("password", user.getPassword());
            profileInfo.put("name", user.getName());
            profileInfo.put("nickname", user.getNickname());
            profileInfo.put("Email", user.getEmail());
            profileInfo.put("Introduction", user.getIntroduction());
            profileInfo.put("Gender", user.getGender());
            profileInfo.put("Birth", user.getBirth());
            profileInfo.put("Role", user.getRole());
            profileInfo.put("ProfileImg", user.getProfileImg());
            profileInfo.put("Total Follower", user.getFollowingList().size());
            profileInfo.put("Total Following", user.getFollowerList().size());

            profileInfo.put("Music Id", user.getMusicId().getId());
            profileInfo.put("Music Title", user.getMusicId().getTitle());
            profileInfo.put("Music Artist", user.getMusicId().getArtist());
            profileInfo.put("Music Genre", user.getMusicId().getGenre());
            profileInfo.put("Music ImageUrl", user.getMusicId().getImageUrl());
            profileInfo.put("Music PreviewUrl", user.getMusicId().getPreviewUrl());

            return profileInfo;
        } else {
            return Collections.emptyMap();
        }
    }

    public User getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        return user;
    }
}

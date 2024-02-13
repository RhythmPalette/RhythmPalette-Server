package com.umc.Palette.domain.user.service.impl;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.repository.MusicRepository;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.ProfileRequest;
import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.domain.user.service.UserService;
import com.umc.Palette.global.exception.BaseException;
import com.umc.Palette.global.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public User profileSave(ProfileRequest profileRequest, String loginId) {

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setProfileImg(profileRequest.getProfileImg());
            user.setNickname(profileRequest.getNickname());
            user.setIntroduction(profileRequest.getIntroduction());
            user.setBirth(LocalDate.parse(profileRequest.getBirth()));
            user.setGender(profileRequest.getGender());

            user.addPreferenceGenre(profileRequest.getPreferenceGenre1());
            user.addPreferenceGenre(profileRequest.getPreferenceGenre2());
            user.addPreferenceGenre(profileRequest.getPreferenceGenre3());

//            Music music = new Music();
//            music.setId((long) profileRequest.getMusicId());
//            user.setMusicId(music);
//            musicRepository.save(music);
            return userRepository.save(user);

        }
        return null;
    }

}

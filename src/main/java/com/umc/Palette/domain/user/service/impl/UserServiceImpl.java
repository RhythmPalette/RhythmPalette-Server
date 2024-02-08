package com.umc.Palette.domain.user.service.impl;

import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

//    @Override
//    public String checkLoginIdDuplicate(String loginId) {
//        return userRepository.existsByLoginId(loginId);
//    }

//    public boolean checkLoginIdDuplicate(String loginId) {
//        return userRepository.existsByLoginId(loginId);
//    }

}

package com.umc.Palette.domain.user.service;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.ProfileRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();


    User profileSave(ProfileRequest profileRequest, String loginId);




}


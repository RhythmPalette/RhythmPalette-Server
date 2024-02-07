package com.umc.Palette.domain.user.service;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

//    String checkLoginIdDuplicate(String loginId);


}


package com.umc.Palette.domain.user.service;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

//    boolean isEmailAlreadyInUse(String email);

//    String checkLoginIdDuplicate(String loginId);


}


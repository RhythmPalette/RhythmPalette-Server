package com.umc.Palette.domain.user.service;

import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.*;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean checkLoginIdDuplicate(String loginId);

    boolean passwordCheck(String pw1, String pw2);

}

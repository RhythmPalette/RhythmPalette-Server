package com.umc.Palette.domain.user.service.impl;

import com.umc.Palette.domain.user.Role;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.dto.JwtAuthenticationResponse;
import com.umc.Palette.domain.user.dto.RefreshTokenRequest;
import com.umc.Palette.domain.user.dto.SignUpRequest;
import com.umc.Palette.domain.user.dto.SigninRequest;
import com.umc.Palette.domain.user.repository.UserRepository;
import com.umc.Palette.domain.user.service.AuthenticationService;
import com.umc.Palette.domain.user.service.JwtService;
import com.umc.Palette.global.exception.BaseResponse;
import com.umc.Palette.global.exception.BaseResponseService;
import com.umc.Palette.global.exception.BaseResponseStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final BaseResponseService baseResponseService;

    public User signup(SignUpRequest signUpRequest) {

        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setRole(Role.ROLE_USER);
        System.out.println("사용자 패스워드 미리보기: "+ signUpRequest.getPassword());

        // 제약 검증
        if (!signUpRequest.getPassword().matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")) {
            // 제약 조건을 만족하지 않는 경우
            throw new ConstraintViolationException("비밀번호 제약을 만족하지 않습니다.", null);
//            return baseResponseService.getFailureResponse(BaseResponseStatus.NOT_SATISFIED_PW_CRITERIA);
        } else {
            // 제약이 충족된 경우 비밀번호 암호화
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        }

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setNickname(signUpRequest.getNickname());  // 잠시 추가
        user.setIntroduction(signUpRequest.getIntroduction());  // 잠시 추가
        user.setLoginId(signUpRequest.getLoginId());

        return userRepository.save(user);

    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getLoginId(),
                signinRequest.getPassword()));

        var user = userRepository.findByLoginId(signinRequest.getLoginId()).orElseThrow(() -> new IllegalArgumentException("Invalid login_Id or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userLoginId = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByLoginId(userLoginId).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken() );
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public Boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }


    public boolean passwordCheck(String firstpassword, String secondpassword) {
        // 제약 검증
        if (!firstpassword.matches("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")) {
            // 제약 조건을 만족하지 않는 경우
            throw new ConstraintViolationException("비밀번호 제약을 만족하지 않습니다.", null);
        }

        if (firstpassword.equals(secondpassword)){
            return true;
        }
        else
            return false;
    }
}

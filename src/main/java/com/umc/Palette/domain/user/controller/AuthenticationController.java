package com.umc.Palette.domain.user.controller;

import com.umc.Palette.domain.user.dto.*;
import com.umc.Palette.domain.user.service.AuthenticationService;
import com.umc.Palette.global.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.umc.Palette.global.exception.BaseResponseStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final BaseResponseService baseResponseService;

    private final CustomExceptionHandler customExceptionHandler;

    // 회원가입 - (1) 아이디 중복 확인
    @GetMapping("signup/{loginId}")
    public BaseResponse<Object> checkLoginIdDuplicate(@PathVariable(name = "loginId") String loginId) {
        try {
            return authenticationService.checkLoginIdDuplicate(loginId)
                    ? baseResponseService.getFailureResponse(ALREADY_EXISTS)
                    : baseResponseService.getSuccessResponse(NOT_EXISTS_LOGIN_ID);
        } catch (Exception e) {
            return customExceptionHandler.handleGeneralException(e).getBody();
        }
    }

    // 회원가입 - (2) 비밀번호 조건 확인
    @PostMapping("/signup/passwordCheck")
    public BaseResponse<Object> passwordCheck(@RequestBody PasswordCheckRequest passwordCheckRequest) {
        try {
            return authenticationService.passwordCheck(passwordCheckRequest.getFirstPassword(), passwordCheckRequest.getSecondPassword())
                    ? baseResponseService.getSuccessResponse(SATISFIED_PW_CRITERIA)
                    : baseResponseService.getFailureResponse(NOT_SATISFIED_PW_CRITERIA);
        } catch (ConstraintViolationException e) {
            return baseResponseService.getFailureResponse(NOT_SATISFIED_PW_CRITERIA);
        } catch (Exception e){
            return customExceptionHandler.handleGeneralException(e).getBody();
        }
    }

    /**
     *  회원가입 - (3) 이메일 인증 확인 (MailController에서 진행)
     *  회원가입 - (4) 유저가 받은 인증번호 일치 확인 (MailController에서 진행)
     */

    // 회원가입 - (5) 최종 회원가입 완료
    @PostMapping("/signup")
    public BaseResponse<Object> signup(@RequestBody SignUpRequest signUpRequest) {
        try{
            authenticationService.signup(signUpRequest);
        } catch(ConstraintViolationException e) {
            return baseResponseService.getFailureResponse(NOT_SATISFIED_PW_CRITERIA);
        } catch (Exception e) {
            return baseResponseService.getSuccessResponse(NOT_EQUAL_PW);
        }
        return baseResponseService.getSuccessResponse(SIGN_UP_COMPLETE);

    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));

    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));

    }





}

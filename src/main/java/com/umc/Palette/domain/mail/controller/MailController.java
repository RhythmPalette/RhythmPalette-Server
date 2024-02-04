package com.umc.Palette.domain.mail.controller;

import com.umc.Palette.domain.mail.dto.EmailAuthRequest;
import com.umc.Palette.domain.mail.dto.EmailCheckRequest;
import com.umc.Palette.domain.mail.service.EmailService;
import com.umc.Palette.domain.mail.service.EmailServiceImpl;
import com.umc.Palette.global.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.umc.Palette.global.exception.BaseResponseStatus.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class MailController {

    private final EmailService emailService;

    private final BaseResponseService baseResponseService;

    private final CustomExceptionHandler customExceptionHandler;


    // 회원가입 - (3) 유저 이메일로 인증번호 전송
    @PostMapping("/signup/emailCheck")
    public BaseResponse<Object> emailConfirm(@RequestBody EmailCheckRequest emailCheckRequest) {
        String confirm;
        try {
            confirm = emailService.sendSimpleMessage(emailCheckRequest.getEmail());
        } catch (BaseException e){
            return baseResponseService.getFailureResponse(UNABLE_TO_SEND_EMAIL);
        }
        catch (Exception e) {
            return customExceptionHandler.handleGeneralException(e).getBody();
        }
        return new BaseResponse<>(true,"인증번호 값 전송" ,2002 ,confirm);

    }

    // 회원가입 - (4) 유저가 받은 인증번호 일치 확인
    @PostMapping("/signup/emailAuthentication")
    public BaseResponse<Object> ememailAuthentication(@RequestBody EmailAuthRequest emailAuthRequest) {
        try {
            return emailAuthRequest.getCertificationNum().equals(EmailServiceImpl.ePw)
                    ? baseResponseService.getSuccessResponse(EQUAL_CERTIFICATION_NUM)
                    : baseResponseService.getFailureResponse(NOT_EQUAL_CERTIFICATION_NUM);
        } catch (Exception e) {
            return customExceptionHandler.handleGeneralException(e).getBody();
        }
    }
}

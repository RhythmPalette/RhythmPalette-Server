package com.umc.Palette.domain.kakao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.umc.Palette.domain.kakao.domain.KakaoApi;
import com.umc.Palette.domain.user.service.AuthenticationService;
import com.umc.Palette.global.exception.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoApi kakaoApi;
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("kakaoApiKey", kakaoApi.getKakaoApiKey());
        model.addAttribute("redirectUri", kakaoApi.getKakaoRedirectUri());
        return "kakaologin";
    }

    @RequestMapping("/login/oauth2/code/kakao")
    public String loginByKakao(@RequestParam("code") String code) throws JsonProcessingException {

        System.out.println("인가 코드 받기 시작");
        // 1. 인가 코드 받기 (@RequestParam String code)

        // 2. 토큰 받기
        System.out.println("토큰 받기 시작");
        String accessToken = kakaoApi.getAccessToken(code);
        System.out.println("accessToken: "+ accessToken);

        // 3. 사용자 정보 받기
        System.out.println("사용자 정보 받기 시작");
        Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        String email = (String)userInfo.get("email");
        String nickname = (String)userInfo.get("nickname");

        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
        System.out.println("accessToken = " + accessToken);

        authenticationService.kakaoSignup(email,nickname);
        // 이메일 , 닉네임을 받는다. 그럼 비밀번호를 닉네임으로...??

        return "redirect:/result";
    }


}
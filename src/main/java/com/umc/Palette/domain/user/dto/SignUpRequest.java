package com.umc.Palette.domain.user.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String loginId;
    private String email;
    private String password;
    private String nickname;
    private String introduction;
}

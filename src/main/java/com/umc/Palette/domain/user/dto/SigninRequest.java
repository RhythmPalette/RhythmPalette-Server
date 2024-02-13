package com.umc.Palette.domain.user.dto;

import lombok.Data;

@Data
public class SigninRequest {

    private String loginId;
    private String password;
}

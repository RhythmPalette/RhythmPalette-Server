package com.umc.Palette.domain.user.dto;

import lombok.Data;

@Data
public class PasswordCheckRequest {
    private String firstPassword;
    private String secondPassword;
}

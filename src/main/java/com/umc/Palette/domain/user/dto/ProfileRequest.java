package com.umc.Palette.domain.user.dto;

import com.umc.Palette.domain.music.domain.Music;
import com.umc.Palette.domain.music.dto.request.MusicRequest;
import lombok.Data;

@Data
public class ProfileRequest {
    private String nickname;        // 필수
    private String introduction;    // 필수
    private String birth;           // 선택
    private Character gender;          // 선택

    private int preferenceGenre1;    // 선호 장르
    private int preferenceGenre2;
    private int preferenceGenre3;
    private MusicRequest musicRequest;

}

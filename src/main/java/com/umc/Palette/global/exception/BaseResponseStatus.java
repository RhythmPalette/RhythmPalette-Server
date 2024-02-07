package com.umc.Palette.global.exception;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    // -------- 성공 코드 시작 -------- //
    SUCCESS(true, 1000, "요청에 성공했습니다."),
    // -------- 성공 코드 종료 -------- //



    // -------- 실패 코드 시작 -------- //
    // -------- 필요한 에러 코드 추가 => Code 만들 때 안겹치게 몇번대 사용할 건지 얘기할 것  -------- //
    /**
     * User
     * Code : 2000
     */
    INVALID_PASSWORD(false, 2000, "아이디 또는 비밀번호가 틀렸습니다."),
    ALREADY_EXISTS(false, 20001, "아이디가 이미 존재합니다."),
    ENTITY_NOT_FOUND(false, 2002,"entity not found"),
    USER_NOT_FOUND(false, 2003, "해당하는 유저 정보가 없습니다."),
    EMPTY_TOKEN(false, 2004, "토큰을 확인해주세요."),
    ;

    // -------- 실패 코드 종료 -------- //

    private boolean isSuccess; // 성공 여부
    private String message; // 메시지
    private int code; // 코드
    /**
     * BaseResponseStatus 에서 해당하는 코드를 매핑
     *
     * @param isSuccess
     * @param code
     * @param message
     */

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
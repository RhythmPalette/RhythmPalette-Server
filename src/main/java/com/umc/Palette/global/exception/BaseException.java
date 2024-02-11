package com.umc.Palette.global.exception;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseException extends Exception {

    public BaseResponseStatus status;
}



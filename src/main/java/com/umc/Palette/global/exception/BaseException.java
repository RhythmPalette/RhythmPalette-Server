package com.umc.Palette.global.exception;
import lombok.*;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    public BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
    }
}



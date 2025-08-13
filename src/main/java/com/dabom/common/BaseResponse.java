package com.dabom.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse<T> {

    private final T data;
    private final Integer code;
    private final String message;

    private BaseResponse(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> BaseResponse<T> of(T data, HttpStatus status) {
        return new BaseResponse<>(data, status.value(), "");
    }

    public static <T> BaseResponse<T> of(T data, HttpStatus status, String message) {
        return new BaseResponse<>(data, status.value(), message);
    }
}

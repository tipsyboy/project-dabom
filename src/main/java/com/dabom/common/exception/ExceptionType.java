package com.dabom.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionType {
    HttpStatus statusCode();
    String message();
}

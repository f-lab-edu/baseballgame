package com.baseball.global.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import com.baseball.global.domain.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String message, ErrorCode errorCode) {
        super(message + " " + errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatusCode() {
        return errorCode.getHttpStatus();
    }
}
package com.baseball.global.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ErrorCode {
    CLOSED_GAME("CLOSED_GAME", "", HttpStatus.OK)
    ,DATA_NOT_FOUND("DATA_NOT_FOUND", "데이터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST)
    ,INVALID_PARAMETER("INVALID_PARAMETER", "유효하지 않은 파라미터입니다.", HttpStatus.BAD_REQUEST)
    ,INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "예기치 못한 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    String code;
    String message;
    HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

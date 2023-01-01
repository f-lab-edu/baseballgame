package com.baseball.global.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baseball.game.dto.ApiResult;
import com.baseball.game.dto.data.ErrorData;
import com.baseball.global.exception.BusinessException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult> handle(BusinessException e) {
        ErrorData errorData = new ErrorData(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return new ResponseEntity(ApiResult.error(errorData), e.getErrorCode().getHttpStatus());
    }
}

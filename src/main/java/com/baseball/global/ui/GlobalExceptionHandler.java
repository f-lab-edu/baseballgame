package com.baseball.global.ui;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baseball.game.dto.ApiResult;
import com.baseball.game.dto.data.ErrorData;
import com.baseball.global.domain.ErrorCode;
import com.baseball.global.exception.BusinessException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation 에러
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResult> handle(BindException e) {
        List<ErrorData.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorData.ValidationError::of)
                .collect(Collectors.toList());
        return new ResponseEntity(ApiResult.error(getErrorData(ErrorCode.INVALID_PARAMETER, validationErrorList)), ErrorCode.INVALID_PARAMETER.getHttpStatus());
    }

    // 커스텀 에러
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult> handle(BusinessException e) {
        ErrorData errorData = ErrorData.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build();
        return new ResponseEntity(ApiResult.error(errorData), e.getErrorCode().getHttpStatus());
    }

    // 예기치 못한 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult> handle(Exception e) {
        return new ResponseEntity(ApiResult.error(getErrorData(ErrorCode.INVALID_PARAMETER)), ErrorCode.INVALID_PARAMETER.getHttpStatus());
    }

    private ErrorData getErrorData(ErrorCode errorCode) {
        ErrorData errorData = ErrorData.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return errorData;
    }

    private ErrorData getErrorData(ErrorCode errorCode, List<ErrorData.ValidationError> validationErrorList) {
        ErrorData errorData = ErrorData.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
        return errorData;
    }
}

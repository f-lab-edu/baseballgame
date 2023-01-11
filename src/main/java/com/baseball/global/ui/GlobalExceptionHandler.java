package com.baseball.global.ui;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baseball.global.domain.ErrorCode;
import com.baseball.global.domain.dto.ApiResult;
import com.baseball.global.domain.dto.ErrorDto;
import com.baseball.global.exception.BusinessException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation 에러
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResult> handle(BindException bindException) {
        List<ErrorDto.ValidationError> validationErrorList = bindException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorDto.ValidationError::of)
                .collect(Collectors.toList());
        return new ResponseEntity(ApiResult.error(getErrorData(ErrorCode.INVALID_PARAMETER, validationErrorList)), ErrorCode.INVALID_PARAMETER.getHttpStatus());
    }

    // 커스텀 에러
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult> handle(BusinessException businessException) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(businessException.getErrorCode().getCode())
                .message(businessException.getErrorCode().getMessage())
                .build();
        return new ResponseEntity(ApiResult.error(errorDto), businessException.getErrorCode().getHttpStatus());
    }

    // 예기치 못한 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult> handle(Exception exception) {
        return new ResponseEntity(ApiResult.error(getErrorData(ErrorCode.INVALID_PARAMETER)), ErrorCode.INVALID_PARAMETER.getHttpStatus());
    }

    private ErrorDto getErrorData(ErrorCode errorCode) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return errorDto;
    }

    private ErrorDto getErrorData(ErrorCode errorCode, List<ErrorDto.ValidationError> validationErrorList) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
        return errorDto;
    }
}

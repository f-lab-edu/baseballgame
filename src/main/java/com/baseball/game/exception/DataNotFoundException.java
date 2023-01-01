package com.baseball.game.exception;

import com.baseball.global.domain.ErrorCode;
import com.baseball.global.exception.BusinessException;

public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.DATA_NOT_FOUND);
    }

    public DataNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

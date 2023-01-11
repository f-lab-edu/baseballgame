package com.baseball.game.domain.exception;

import com.baseball.global.domain.ErrorCode;
import com.baseball.global.exception.BusinessException;

public class GameIdNotFoundException extends BusinessException {

    public GameIdNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.GAMEID_NOT_FOUND);
    }

    public GameIdNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}

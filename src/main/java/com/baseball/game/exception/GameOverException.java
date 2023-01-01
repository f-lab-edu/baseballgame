package com.baseball.game.exception;

import com.baseball.global.domain.ErrorCode;
import com.baseball.global.exception.BusinessException;

public class GameOverException extends BusinessException {
    public GameOverException(ErrorCode errorCode) {
        super(ErrorCode.CLOSED_GAME);
    }
}

package com.baseball.game.dto.data;

import lombok.Getter;

@Getter
public class ErrorData {

    private final String code;
    private final String message;

    public ErrorData(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

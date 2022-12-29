package com.baseball.game.ui.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorData {

    private final String code;
    private final String message;

    @Builder
    private ErrorData(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

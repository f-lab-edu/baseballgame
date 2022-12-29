package com.baseball.game.ui.dto;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class ApiResultResponse<T> {

    @JsonProperty("success")
    private final boolean success;

    @JsonProperty("data")
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    private final ErrorData error;

    public ApiResultResponse(boolean success, T data, ErrorData error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}

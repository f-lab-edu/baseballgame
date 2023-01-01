package com.baseball.game.dto;

import lombok.Getter;
import lombok.ToString;

import com.baseball.game.dto.data.ErrorData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
@Getter
public class ApiResult<T> {

    @JsonProperty("success")
    private final boolean success;

    @JsonProperty("data")
    private final T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    private final ErrorData error;

    private ApiResult(boolean success, T data, ErrorData error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static ApiResult error(ErrorData errorData) {
        return new ApiResult(false, null, errorData);
    }
}

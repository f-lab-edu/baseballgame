package com.baseball.global.domain.dto;

import lombok.Getter;
import lombok.ToString;

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
    private final ErrorDto error;

    private ApiResult(boolean success, T data, ErrorDto error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static ApiResult error(ErrorDto errorDto) {
        return new ApiResult(false, null, errorDto);
    }
}

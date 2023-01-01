package com.baseball.game.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
@Getter
@ToString
public class GuessRequest {

    @NotBlank(message = "정답을 입력해주세요")
    @Size(min = 3, max = 3, message = "3자리의 숫자만 입력해주세요")
    @Pattern(regexp = "^[0-9]{1,3}", message = "숫자만 입력해주세요")
    @JsonProperty("answer")
    private String playerNumber;

    @Builder
    private GuessRequest(String playerNumber) {
        this.playerNumber = playerNumber;
    }
}

package com.baseball.game.domain.play.dto;

import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@ToString
public class HistoryDto {

    @JsonProperty("answer")
    private final String playerAnswer;
    @JsonProperty("result")
    private final ResultData resultData;

    public HistoryDto(String playerAnswer, int strike, int ball, int out) {
        this.playerAnswer = playerAnswer;
        this.resultData = new ResultData(strike, ball, out);
    }

    @Getter
    @ToString
    class ResultData {
        private final int strike;
        private final int ball;
        private final int out;

        public ResultData(int strike, int ball, int out) {
            this.strike = strike;
            this.ball = ball;
            this.out = out;
        }
    }

}

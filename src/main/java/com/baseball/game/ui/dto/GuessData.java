package com.baseball.game.ui.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuessData {
    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;

    public GuessData(boolean correct, int remainingCount, int strike, int ball, int out) {
        this.correct = correct;
        this.remainingCount = remainingCount;
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }
}

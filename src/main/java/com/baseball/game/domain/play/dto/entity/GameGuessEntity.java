package com.baseball.game.domain.play.dto.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameGuessEntity {

    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;

    @Builder
    private GameGuessEntity(boolean correct, int remainingCount, int strike, int ball, int out) {
        this.correct = correct;
        this.remainingCount = remainingCount;
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }
}

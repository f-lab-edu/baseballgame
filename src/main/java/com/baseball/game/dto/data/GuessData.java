package com.baseball.game.dto.data;

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

    public boolean isCorrect() {
        return strike == 3 ? true : false;
    }

    public int getOut() {
        return 3 - (strike + ball);
    }

}

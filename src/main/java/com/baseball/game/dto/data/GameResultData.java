package com.baseball.game.dto.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.baseball.global.domain.BaseballConstants;

@Getter
@Setter
@ToString
public class GameResultData {
    private int remainingCount;
    private int answerCount;

    public int getRemainingCount() {
        return BaseballConstants.MAX_ANSWER - answerCount;
    }
}

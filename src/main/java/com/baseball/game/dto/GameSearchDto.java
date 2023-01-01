package com.baseball.game.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameSearchDto {

    private Long gameId;
    private String playerAnswer;

    @Builder
    private GameSearchDto(Long gameId, String playerAnswer) {
        this.gameId = gameId;
        this.playerAnswer = playerAnswer;
    }
}

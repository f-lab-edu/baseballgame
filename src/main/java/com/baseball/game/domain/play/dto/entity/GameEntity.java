package com.baseball.game.domain.play.dto.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameEntity {
    private Long gameId;
    private String comAnswer;

    private GameGuessEntity gameGuessEntity;

    private boolean gameOver;

    @Builder
    private GameEntity(Long gameId, String comAnswer, GameGuessEntity gameGuessEntity, boolean gameOver) {
        this.gameId = gameId;
        this.comAnswer = comAnswer;
        this.gameGuessEntity = gameGuessEntity;
        this.gameOver = gameOver;
    }
}

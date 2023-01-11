package com.baseball.game.domain.play.repository;

import com.baseball.game.domain.play.dto.GameDto;
import com.baseball.game.domain.play.dto.entity.GameEntity;

public interface BaseballRepository {

    GameEntity saveGameId(String computerAnswer);

    GameEntity updateGameData(GameEntity gameEntity);

    int saveHistoryData(GameDto gameDto);

}

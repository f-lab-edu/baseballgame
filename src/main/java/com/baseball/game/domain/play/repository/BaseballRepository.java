package com.baseball.game.domain.play.repository;

import com.baseball.game.dto.data.GameData;

public interface BaseballRepository {

    GameData saveGameId(String computerAnswer);

    GameData updateGameData(GameData gameData);

    int saveHistoryData(GameData gameData);

}

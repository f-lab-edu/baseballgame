package com.baseball.game.domain.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.baseball.game.dto.GameSearchDto;
import com.baseball.game.dto.data.GameData;
import com.baseball.game.dto.data.HistoryData;

@Repository
public class BaseballRepository {

    private static long gameId = 122L;
    private Map<Long, GameData> storeTableGame = new ConcurrentHashMap<>();
    private Map<Long, List<HistoryData>> storeTableHistory = new ConcurrentHashMap<>();

    public GameData saveGameId(String computerAnswer) {
        GameData gameData = new GameData();
        gameData.setComputerAnswer(computerAnswer);
        gameData.setGameId(++gameId);
        storeTableGame.put(gameData.getGameId(), gameData);
        return gameData;
    }

    public GameData updateGameData(GameData gameData) {
        storeTableGame.put(gameData.getGameId(), gameData);
        return gameData;
    }

    public GameData findByGameId(GameSearchDto gameSearchDto) {
        return storeTableGame.get(gameSearchDto.getGameId());
    }

    public List<HistoryData> findByHistory(long gameId) {
        return storeTableHistory.get(gameId) == null ? new ArrayList<>() : storeTableHistory.get(gameId);
    }

    public void saveHistoryData(GameData gameData) {
        storeTableHistory.put(gameData.getGameId(), gameData.getHistoryData());
    }

}

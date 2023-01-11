package com.baseball.game.infrastruture;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.baseball.game.domain.play.dto.GameDto;
import com.baseball.game.domain.play.dto.HistoryDto;
import com.baseball.game.domain.play.dto.entity.GameEntity;
import com.baseball.game.domain.play.repository.BaseballReader;
import com.baseball.game.domain.play.repository.BaseballRepository;

@Slf4j
@Repository
public class JpaBaseballRepository implements BaseballRepository, BaseballReader {

    private static AtomicLong atomicGameId = new AtomicLong(122L);
    private Map<Long, GameEntity> storeTableGame = new ConcurrentHashMap<>();
    private Map<Long, List<HistoryDto>> storeTableHistory = new ConcurrentHashMap<>();

    @Override
    public boolean existsByGameId(Long gameId) {
        return false;
    }

    @Override
    public Optional<GameEntity> findByGameId(Long gameId) {
        return Optional.ofNullable(storeTableGame.get(gameId));
    }

    @Override
    public List<HistoryDto> findByHistory(Long gameId) {
        return storeTableHistory.get(gameId) == null ? new ArrayList<>() : storeTableHistory.get(gameId);
    }

    @Override
    public GameEntity saveGameId(String computerAnswer) {
        GameEntity gameEntity = GameEntity.builder()
                .comAnswer(computerAnswer)
                .gameId(atomicGameId.getAndSet(atomicGameId.get() + 1))
                .build();
        storeTableGame.put(gameEntity.getGameId(), gameEntity);
        return gameEntity;
    }

    @Override
    public GameEntity updateGameData(GameEntity gameEntity) {
        storeTableGame.put(gameEntity.getGameId(), gameEntity);
        return gameEntity;
    }

    @Override
    public int saveHistoryData(GameDto gameDto) {
        storeTableHistory.put(gameDto.getGameId(), gameDto.getHistoryData());
        return storeTableHistory.get(gameDto.getGameId()).size() >= 0 ? 1 : 0;
    }
}

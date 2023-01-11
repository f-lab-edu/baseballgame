package com.baseball.game.domain.play.repository;

import java.util.List;
import java.util.Optional;
import com.baseball.game.dto.data.GameData;
import com.baseball.game.dto.data.HistoryData;

public interface BaseballReader {

    boolean existsByGameId(Long gameId);

    Optional<GameData> findByGameId(Long gameId);

    List<HistoryData> findByHistory(Long gameId);
}

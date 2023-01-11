package com.baseball.game.domain.play.repository;

import java.util.List;
import java.util.Optional;

import com.baseball.game.domain.play.dto.HistoryDto;
import com.baseball.game.domain.play.dto.entity.GameEntity;

public interface BaseballReader {

    boolean existsByGameId(Long gameId);

    Optional<GameEntity> findByGameId(Long gameId);

    List<HistoryDto> findByHistory(Long gameId);
}

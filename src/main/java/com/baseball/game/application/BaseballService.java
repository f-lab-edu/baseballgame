package com.baseball.game.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baseball.game.domain.play.BaseballRepository;
import com.baseball.game.domain.play.Computer;
import com.baseball.game.dto.GameSearchDto;
import com.baseball.game.dto.data.GameData;
import com.baseball.game.dto.data.GameResultData;
import com.baseball.game.dto.data.GuessData;
import com.baseball.game.dto.data.HistoryData;
import com.baseball.game.exception.DataNotFoundException;
import com.baseball.game.exception.GameOverException;
import com.baseball.global.domain.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseballService {

    private final BaseballRepository baseballRepository;
    private final Computer computer;

    public GameData getGameId() {
        return baseballRepository.saveGameId(computer.generateOfStrAnswer());
    }

    public GuessData checkAnswer(GameSearchDto gameSearchDto) {
        GameData gameData = validBaseballGame(gameSearchDto);
        computer.judgeAnswer(gameData);
        baseballRepository.saveHistoryData(gameData);
        baseballRepository.updateGameData(gameData);
        return gameData.getGuessData();
    }

    public List<HistoryData> getHistory(long gameId) {
        return baseballRepository.findByHistory(gameId);
    }

    public GameResultData getGameResult(long gameId) {
        GameResultData gameResultData = new GameResultData();
        gameResultData.setAnswerCount(baseballRepository.findByHistory(gameId).size());
        return gameResultData;
    }

    private GameData validBaseballGame(GameSearchDto gameSearchDto) {

        GameData gameData = baseballRepository.findByGameId(gameSearchDto);

        if (gameData == null) {
            throw new DataNotFoundException(ErrorCode.DATA_NOT_FOUND);
        }

        if (gameData.isGameOver()) {
            throw new GameOverException(ErrorCode.CLOSED_GAME);
        }

        log.info("Computer Answer [{}], Player Answer [{}]", gameData.getComputerAnswer(), gameSearchDto.getPlayerAnswer());

        gameData.setPlayerAnswer(gameSearchDto.getPlayerAnswer());
        gameData.setHistoryData(baseballRepository.findByHistory(gameSearchDto.getGameId()));

        return gameData;
    }

}

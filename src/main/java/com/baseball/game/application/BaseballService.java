package com.baseball.game.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baseball.game.domain.exception.DataNotFoundException;
import com.baseball.game.domain.exception.GameOverException;
import com.baseball.game.domain.play.Computer;
import com.baseball.game.domain.play.dto.GameDto;
import com.baseball.game.domain.play.dto.GameResultDto;
import com.baseball.game.domain.play.dto.GuessDto;
import com.baseball.game.domain.play.dto.HistoryDto;
import com.baseball.game.domain.play.mapstruct.GameMapper;
import com.baseball.game.infrastruture.JpaBaseballRepository;
import com.baseball.game.ui.dto.GameSearchDto;
import com.baseball.global.domain.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseballService {

    private final GameMapper gameMapper;
    private final JpaBaseballRepository baseballRepository;
    private final Computer computer;

    public GameDto getGameId() {
        return gameMapper.toDto(baseballRepository.saveGameId(computer.getGenerateOfAnswerStr()));
    }

    public GuessDto checkAnswer(GameSearchDto gameSearchDto) {
        GameDto gameDto = validBaseballGame(gameSearchDto);
        gameDto.judgeAnswer(gameDto);
        baseballRepository.saveHistoryData(gameDto);
        baseballRepository.updateGameData(gameMapper.toEntity(gameDto));
        return gameDto.getGuessDto();
    }

    public List<HistoryDto> getHistory(long gameId) {
        validBaseBallGame(gameId);
        return baseballRepository.findByHistory(gameId);
    }

    public GameResultDto getGameResult(long gameId) {
        validBaseBallGame(gameId);
        GameResultDto gameResultDto = new GameResultDto();
        gameResultDto.setAnswerCount(baseballRepository.findByHistory(gameId).size());
        return gameResultDto;
    }

    private GameDto validBaseballGame(GameSearchDto gameSearchDto) {
        GameDto gameDto = validBaseBallGame(gameSearchDto.getGameId());
        if (gameDto.isGameOver()) {
            throw new GameOverException(ErrorCode.CLOSED_GAME);
        }
        gameDto.setPlayerAnswer(gameSearchDto.getPlayerAnswer());
        gameDto.setHistoryData(baseballRepository.findByHistory(gameSearchDto.getGameId()));

        log.info("Computer Answer [{}], Player Answer [{}]", gameDto.getComputerAnswer(), gameSearchDto.getPlayerAnswer());
        return gameDto;
    }

    private GameDto validBaseBallGame(Long gameId) {
        GameDto gameDto = gameMapper.toDto(baseballRepository.findByGameId(gameId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.GAMEID_NOT_FOUND)));
        return gameDto;
    }

}

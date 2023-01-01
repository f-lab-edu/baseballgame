package com.baseball.game.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.game.application.BaseballService;
import com.baseball.game.dto.ApiResult;
import com.baseball.game.dto.GameSearchDto;
import com.baseball.game.dto.GuessRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/game", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseballController {

    private final BaseballService baseballService;

    // 게임 시작
    @PostMapping(value = "/start")
    public ResponseEntity<ApiResult> gameStart() {
        return new ResponseEntity(ApiResult.success(baseballService.getGameId()), HttpStatus.OK);
    }

    // 게임 진행
    @PostMapping(value = "/{gameId}/guess")
    public ResponseEntity<ApiResult> gameGuess(@PathVariable("gameId") Long gameId,
                            @Validated @RequestBody GuessRequest requestGuess) {
        return new ResponseEntity(ApiResult.success(
                baseballService.checkAnswer(GameSearchDto.builder().gameId(gameId).playerAnswer(requestGuess.getPlayerNumber()).build())
        ), HttpStatus.OK);
    }

    // 게임 결과
    @GetMapping(value = "/{gameId}")
    public ResponseEntity<ApiResult> gameResult(@PathVariable("gameId") Long gameId) {
        return new ResponseEntity(ApiResult.success(baseballService.getGameResult(gameId)), HttpStatus.OK);
    }

    // 게임 진행 기록
    @GetMapping(value = "/{gameId}/history")
    public ResponseEntity<ApiResult> gameHistory(@PathVariable("gameId") Long gameId) {
        return new ResponseEntity(ApiResult.success(baseballService.getHistory(gameId)), HttpStatus.OK);
    }


}


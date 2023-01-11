package com.baseball.game.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.game.application.BaseballService;
import com.baseball.game.ui.dto.GameSearchDto;
import com.baseball.game.ui.dto.GuessRequest;
import com.baseball.global.domain.dto.ApiResult;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/game",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BaseballController {

    private final BaseballService baseballService;

    // 게임 시작
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/start")
    public ApiResult gameStart() {
        return ApiResult.success(baseballService.getGameId());
    }

    // 게임 진행
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{gameId}/guess")
    public ApiResult gameGuess(@PathVariable("gameId") Long gameId,
                            @Validated @RequestBody GuessRequest requestGuess) {
        return ApiResult.success(
                baseballService.checkAnswer(GameSearchDto.builder().gameId(gameId).playerAnswer(requestGuess.getPlayerNumber()).build())
        );
    }

    // 게임 결과
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{gameId}")
    public ApiResult gameResult(@PathVariable("gameId") Long gameId) {
        return ApiResult.success(baseballService.getGameResult(gameId));
    }

    // 게임 진행 기록
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{gameId}/history")
    public ApiResult gameHistory(@PathVariable("gameId") Long gameId) {
        return ApiResult.success(baseballService.getHistory(gameId));
    }


}


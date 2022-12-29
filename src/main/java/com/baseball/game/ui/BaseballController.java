package com.baseball.game.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.game.ui.dto.ApiResultResponse;
import com.baseball.game.ui.dto.GuessData;
import com.baseball.game.ui.dto.GuessRequest;

@Slf4j
@RestController
@RequestMapping(value = "/game", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BaseballController {

    // 게임 시작
    @PostMapping(value = "/start")
    public String gameStart() {
        return "게임 시작";
    }

    // 게임 진행
    @PostMapping(value = "/{gameId}/guess")
    public ResponseEntity<ApiResultResponse> gameGuess(@PathVariable("gameId") Long gameId,
                                                       @RequestBody GuessRequest requestGuess) {
        log.info("gameId={}", gameId);
        log.info("requestGuess={}", requestGuess);
        // 게임 종료가 아닐시
        // 게임 종료 시
        return new ResponseEntity(new ApiResultResponse<GuessData>(true, null, null), HttpStatus.OK);
    }

    // 게임 결과
    @GetMapping(value = "/{gameId}")
    public String gameResult(@PathVariable("gameId") Long gameId) {
        // 게임 결과
        return "게임 결과";
    }

    // 게임 진행 기록
    @GetMapping(value = "/{gameId}/history")
    public String gameHistory(@PathVariable("gameId") Long gameId) {

        return "게임 진행 기록";
    }


}

package com.baseball.game.domain.play.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import com.baseball.global.domain.BaseballConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString
public class GameDto {

    @JsonProperty("gameId")
    private Long gameId;

    @JsonIgnore
    private String computerAnswer;

    @JsonIgnore
    private String playerAnswer;

    @JsonIgnore
    private boolean gameOver;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GuessDto guessDto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HistoryDto> historyData;

    public GameDto judgeAnswer(GameDto gameDto) {
        gameDto.createGuessDataAndCounting(gameDto);
        gameDto.createHistoryData(gameDto);
        return gameDto;
    }

    private void createGuessDataAndCounting(GameDto gameDto) {
        GuessDto guessDto = new GuessDto();
        guessDto.setStrike(gameDto.countingStrike(gameDto.getComputerAnswer(), gameDto.getPlayerAnswer()));
        guessDto.setBall(gameDto.countingBall(gameDto.getComputerAnswer(), gameDto.getPlayerAnswer()) - guessDto.getStrike());
        guessDto.setRemainingCount(BaseballConstants.MAX_ANSWER - (gameDto.getHistoryData().size() + 1));
        gameDto.setGuessDto(guessDto);
        gameDto.setGameOver(isGameOver(gameDto));
    }

    private void createHistoryData(GameDto gameDto) {
        gameDto.getHistoryData().add(
                new HistoryDto(gameDto.getPlayerAnswer(), gameDto.getGuessDto().getStrike(), gameDto.getGuessDto().getBall(), gameDto.getGuessDto().getOut())
        );
    }

    private int countingStrike(String computerAnswer, String playerAnswer) {
        int strike = 0;
        for (char answerNumber : computerAnswer.toCharArray()) {
            if (computerAnswer.indexOf(answerNumber) == playerAnswer.indexOf(answerNumber)) {
                strike++;
            }
        }
        return strike;
    }

    private int countingBall(String computerAnswer, String playerAnswer) {
        int ball = 0;
        for (int i = 0; i < computerAnswer.length(); i++) {
            if (playerAnswer.contains(Character.toString(computerAnswer.charAt(i)))) {
                ball += 1;
            }
        }
        return ball;
    }

    private boolean isGameOver(GameDto gameDto) {
        return gameDto.getGuessDto().getStrike() == 3 ? true : gameDto.getGuessDto().getRemainingCount() == 0 ? true : false;
    }

}

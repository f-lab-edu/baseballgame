package com.baseball.game.domain.play;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.baseball.game.domain.play.dto.GameDto;
import com.baseball.game.domain.play.dto.GuessDto;
import com.baseball.game.domain.play.dto.HistoryDto;
import com.baseball.global.domain.BaseballConstants;

@Slf4j
@Component
public class Computer {

    private final Random random = new Random();

    private int generateOfNumber() {
        return random.nextInt(9) + 1;
    }

    public List<Integer> generateOfAnswer() {
        List<Integer> computerAnswer = new ArrayList<>();
        while (computerAnswer.size() < 3) {
            int randomNumber = generateOfNumber();
            if (!computerAnswer.contains(randomNumber)) {
                computerAnswer.add(randomNumber);
            }
        }
        return computerAnswer;
    }

    public String generateOfStrAnswer() {
        String computerAnswer = "";
        while (computerAnswer.length() < 3) {
            String randomNumber = String.valueOf(generateOfNumber());
            if (!computerAnswer.contains(randomNumber)) {
                computerAnswer = computerAnswer + randomNumber;
            }
        }
        return computerAnswer;
    }

    public GameDto judgeAnswer(GameDto gameDto) {
        createGuessDataAndCounting(gameDto);
        createHistoryData(gameDto);
        return gameDto;
    }

    private void createGuessDataAndCounting(GameDto gameDto) {
        GuessDto guessDto = new GuessDto();
        guessDto.setStrike(countingStrike(gameDto.getComputerAnswer(), gameDto.getPlayerAnswer()));
        guessDto.setBall(countingBall(gameDto.getComputerAnswer(), gameDto.getPlayerAnswer()) - guessDto.getStrike());
        guessDto.setRemainingCount(BaseballConstants.MAX_ANSWER - (gameDto.getHistoryData().size() + 1));
        gameDto.setGuessDto(guessDto);
        gameDto.setGameOver(isGameOver(gameDto));
    }

    private void createHistoryData(GameDto gameDto) {
        gameDto.getHistoryData().add(
                new HistoryDto(gameDto.getPlayerAnswer(), gameDto.getGuessDto().getStrike(), gameDto.getGuessDto().getBall(), gameDto.getGuessDto().getOut())
        );
    }

    private boolean isGameOver(GameDto gameDto) {
        return gameDto.getGuessDto().getStrike() == 3 ? true : gameDto.getGuessDto().getRemainingCount() == 0 ? true : false;
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

}

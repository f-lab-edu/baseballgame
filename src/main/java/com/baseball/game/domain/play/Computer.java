package com.baseball.game.domain.play;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.baseball.game.dto.data.GameData;
import com.baseball.game.dto.data.GuessData;
import com.baseball.game.dto.data.HistoryData;
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

    public GameData judgeAnswer(GameData gameData) {
        createGuessDataAndCounting(gameData);
        createHistoryData(gameData);
        return gameData;
    }

    private void createGuessDataAndCounting(GameData gameData) {
        GuessData guessData = new GuessData();
        guessData.setStrike(countingStrike(gameData.getComputerAnswer(), gameData.getPlayerAnswer()));
        guessData.setBall(countingBall(gameData.getComputerAnswer(), gameData.getPlayerAnswer()) - guessData.getStrike());
        guessData.setRemainingCount(BaseballConstants.MAX_ANSWER - (gameData.getHistoryData().size() + 1));
        gameData.setGuessData(guessData);
        gameData.setGameOver(isGameOver(gameData));
    }

    private void createHistoryData(GameData gameData) {
        gameData.getHistoryData().add(
                new HistoryData(gameData.getPlayerAnswer(), gameData.getGuessData().getStrike(), gameData.getGuessData().getBall(), gameData.getGuessData().getOut())
        );
    }

    private boolean isGameOver(GameData gameData) {
        return gameData.getGuessData().getStrike() == 3 ? true : gameData.getGuessData().getRemainingCount() == 0 ? true : false;
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

package com.baseball.game.infrastruture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.baseball.game.domain.play.Computer;

@Component
public class ComputerProcessor implements Computer {

    private final Random random = new Random();

    private int generateOfNumber() {
        return random.nextInt(9) + 1;
    }

    @Override
    public List<Integer> getGenerateOfAnswerList() {
        List<Integer> computerAnswer = new ArrayList<>();
        while (computerAnswer.size() < 3) {
            int randomNumber = generateOfNumber();
            if (!computerAnswer.contains(randomNumber)) {
                computerAnswer.add(randomNumber);
            }
        }
        return computerAnswer;
    }

    @Override
    public String getGenerateOfAnswerStr() {
        String computerAnswer = "";
        while (computerAnswer.length() < 3) {
            String randomNumber = String.valueOf(generateOfNumber());
            if (!computerAnswer.contains(randomNumber)) {
                computerAnswer = computerAnswer + randomNumber;
            }
        }
        return computerAnswer;
    }
}

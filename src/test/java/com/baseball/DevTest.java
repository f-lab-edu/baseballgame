package com.baseball;

import org.junit.jupiter.api.Test;

import com.baseball.game.domain.play.Computer;

public class DevTest {

    @Test
    void countingStrikeCheck() {
        //given
        int strike = 0;
        String answerNumbers = "456";
        String playerNumbers = "354";

        //when
        for (char answerNumber : answerNumbers.toCharArray()) {
            System.out.println("answerNumber = " + answerNumber);
            if (answerNumbers.indexOf(answerNumber) == playerNumbers.indexOf(answerNumber)) {
                strike++;
            }
        }

        //then
        System.out.println("strike = " + strike);
    }

    @Test
    void countingBallCheck() {
        //given
        int ball = 0;
        String answerNumbers = "456";
        String playerNumbers = "354";

        //when
        for (int i = 0; i < answerNumbers.length(); i++) {
            if (playerNumbers.contains(Character.toString(answerNumbers.charAt(i)))) {
                ball += 1;
            }
        }

        //then
        System.out.println("ball = " + ball);
    }

    @Test
    void split() {
        String result = "0,0,3";
        String[] tmp = result.split(",");
        for (String s : tmp) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void randomAnswerList() {
        Computer computer = new Computer();
        System.out.println("computer answer = " + computer.generateOfAnswer());
    }

    @Test
    void randomAnswerString() {
        Computer computer = new Computer();
        System.out.println("computer answer = " + computer.generateOfStrAnswer());
    }


}

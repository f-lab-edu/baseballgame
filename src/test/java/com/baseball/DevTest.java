package com.baseball;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class DevTest {

    @Test
    void countingCharAndIndexOf() {
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


}

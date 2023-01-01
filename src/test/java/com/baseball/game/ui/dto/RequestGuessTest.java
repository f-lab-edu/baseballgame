package com.baseball.game.ui.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.baseball.game.dto.GuessRequest;

import static org.assertj.core.api.Assertions.assertThat;

class RequestGuessTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private boolean isPrint = true;

    @Test
    @DisplayName("빈 값 체크")
    void beanValidationBlank() {
        //given
        GuessRequest requestGuess = GuessRequest.builder().playerNumber("").build();

        //when
        Set<ConstraintViolation<GuessRequest>> violations = validator.validate(requestGuess);
        if (isPrint) printValidation(violations);

        //then
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("입력값이 3자리 인지 체크")
    void beanValidationSize() {
        //given
        GuessRequest requestGuess = GuessRequest.builder().playerNumber("1234").build();

        //when
        Set<ConstraintViolation<GuessRequest>> violations = validator.validate(requestGuess);
        if (isPrint) printValidation(violations);

        //then
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("3자리의 숫자만 입력했는지 체크")
    void beanValidationPattern() {
        //given
        GuessRequest requestGuess = GuessRequest.builder().playerNumber("12A34").build();

        //when
        Set<ConstraintViolation<GuessRequest>> violations = validator.validate(requestGuess);
        if (isPrint) printValidation(violations);

        //then
        assertThat(violations.size()).isEqualTo(2);
    }

    private void printValidation(Set<ConstraintViolation<GuessRequest>> violations) {
        for (ConstraintViolation<GuessRequest> violation : violations) {
            System.out.println("violation = " + violation.getMessage());
        }
    }

}

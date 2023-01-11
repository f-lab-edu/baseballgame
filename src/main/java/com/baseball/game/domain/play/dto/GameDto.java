package com.baseball.game.domain.play.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

}

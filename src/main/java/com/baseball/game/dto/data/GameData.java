package com.baseball.game.dto.data;

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
public class GameData {

    @JsonProperty("gameId")
    private Long gameId;

    @JsonIgnore
    private String computerAnswer;

    @JsonIgnore
    private String playerAnswer;

    @JsonIgnore
    private boolean gameOver;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GuessData guessData;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HistoryData> historyData;

}

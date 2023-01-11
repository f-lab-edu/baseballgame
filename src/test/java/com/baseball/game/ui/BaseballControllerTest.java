package com.baseball.game.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.baseball.game.application.BaseballService;
import com.baseball.game.domain.play.dto.GameDto;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BaseballController.class)
class BaseballControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BaseballService baseballService;

    @Test
    @DisplayName("게임 시작 후 gameId 잘가져오는지 확인")
    void gameStartTest() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setGameId(123L);

        //given
        given(baseballService.getGameId())
                .willReturn(gameDto);
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/game/start")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE));
//                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.gameId").exists())
                .andExpect(jsonPath("$.data.gameId").value(123))
                .andDo(MockMvcResultHandlers.print())
        ;
    }


}

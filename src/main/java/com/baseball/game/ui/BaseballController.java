package com.baseball.game.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class BaseballController {

    @PostMapping("/start")
    public String start() {
        return "ok";
    }


}

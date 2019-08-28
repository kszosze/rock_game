package com.game.rockpaperscissor.controller;

import com.game.rockpaperscissor.model.Game;
import com.game.rockpaperscissor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping(path="/new/{playerName}")
    public Game startGame(@PathVariable String playerName) {
        return gameService.newGame(playerName);
    }

    @PutMapping(path = "/{gameId}")
    public Game playOneHand(@PathVariable Integer gameId) {
        return gameService.playGame(gameId);
    }

    @GetMapping(path = "/{gameId}")
    public Game getGame(@PathVariable Integer gameId) {
        return gameService.getGame(gameId);
    }

    @GetMapping(path = "/all")
    public List<Game> getGames() {
        return gameService.getGames();
    }
}

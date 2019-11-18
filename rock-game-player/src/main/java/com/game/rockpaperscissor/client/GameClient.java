package com.game.rockpaperscissor.client;

import com.game.rockpaperscissor.model.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "gameClient")
public interface GameClient {

    @PostMapping(value = "/game/new/{playerName}")
    Game createPlayer(@PathVariable("playerName")String playerName);

    @PutMapping(value = "/game/{gameId}")
    Game startGame(@PathVariable("gameId") Integer gameId);

    @GetMapping(value = "/game/{gameId}")
    Game getGame(@PathVariable("gameId") Integer gameId);

    @GetMapping(value = "/game/stats")
    List<Game> getAllGameStats();

    @GetMapping(value = "/game/all")
    List<Game> getAllGame();
}

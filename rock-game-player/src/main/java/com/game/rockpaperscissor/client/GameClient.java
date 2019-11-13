package com.game.rockpaperscissor.client;

import com.game.rockpaperscissor.model.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("game")
public interface GameClient {

    @RequestMapping(method = RequestMethod.POST, value = "/new/{playerName}")
    Game createPlayer(@PathVariable("playerName")String playerName);

    @RequestMapping(method = RequestMethod.PUT, value = "/{gameId}")
    Game startGame(@PathVariable("gameId") Integer gameId);

    @RequestMapping(method = RequestMethod.GET, value = "/{gameId}")
    Game getGame(@PathVariable("gameId") Integer gameId);

    @RequestMapping(method = RequestMethod.GET, value = "/stats")
    List<Game> getAllGameStats();

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Game> getAllGame();
}

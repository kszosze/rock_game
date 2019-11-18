package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.client.GameClient;
import com.game.rockpaperscissor.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayService {

    @Autowired
    private GameClient gameClient;

    public Game createPlayer(String playerName) {
        return gameClient.createPlayer(playerName);
    }

    public Game startGame(Integer gameId) {
        return gameClient.startGame(gameId);
    }

    public Game getGame(Integer gameId) {
        return gameClient.getGame(gameId);
    }

    public List<Game> getAllGameStats() {
        return gameClient.getAllGameStats();
    }

    public List<Game> getAllGame() {
        return gameClient.getAllGame();
    }
}

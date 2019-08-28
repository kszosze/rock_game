package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.model.Player;
import com.game.rockpaperscissor.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player generatePlayer(){
        Player player = Player.builder().name("playerRock").build();
        return playerRepository.save(player);
    }

    public Player generatePlayer(String playerName){
        Player player = Player.builder().name(playerName).build();
        return playerRepository.save(player);
    }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}

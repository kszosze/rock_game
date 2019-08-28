package com.game.rockpaperscissor.repository;

import com.game.rockpaperscissor.model.Player;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class PlayerRepository {

    private Map<Integer, Player> playerRepository;

    public PlayerRepository() {
        this.playerRepository = new HashMap<>();
    }

    public Player save(Player player) {
        if (Objects.isNull(player.getId())) {
            player.setId(playerRepository.size() + 1);
        }
        playerRepository.put(player.getId(), player);
        return player;
    }
}

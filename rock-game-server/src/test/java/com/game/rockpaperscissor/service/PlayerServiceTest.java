package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.model.Player;
import com.game.rockpaperscissor.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @Before
    public void setUp() {
        playerService = new PlayerService(playerRepository);
    }
    @Test
    public void generatePlayer() {
        playerService.generatePlayer();
        Mockito.verify(playerRepository).save(any());
    }

    @Test
    public void updatePlayer() {
        playerService.updatePlayer(Player.builder().build());
        Mockito.verify(playerRepository).save(any());
    }
}
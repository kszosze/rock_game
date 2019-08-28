package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.data.StatsEnum;
import com.game.rockpaperscissor.model.Game;
import com.game.rockpaperscissor.model.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.SpringRunner;

import static com.game.rockpaperscissor.data.ChoiceEnum.ROCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GameServiceTest {

    public static final String PLAYER_1 = "player1";
    public static final String PLAYER_2 = "player2";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private PlayerService playerService;

    private GameService gameService;

    @Before
    public void setUp() {
        gameService = new GameService(playerService);
    }

    @Test
    public void generatePlayers() {

        Player player1 = Player.builder().id(0).name(PLAYER_1).build();
        Player player2 = Player.builder().id(1).name(PLAYER_2).build();

        when(playerService.generatePlayer()).thenReturn(player1);
        when(playerService.generatePlayer(anyString())).thenReturn(player2);

        Player[] resultPlayers = gameService.generatePlayers(PLAYER_2);

        assertThat(resultPlayers).hasSize(2);
        assertThat(resultPlayers).containsExactlyInAnyOrder(player1, player2);

    }

    @Test
    public void endGame() {
        Player player1 = Player.builder().id(0).name(PLAYER_1).build();
        Player player2 = Player.builder().id(1).name(PLAYER_2).build();

        Player[] playersList = new Player[] { player1, player2 };

        Game game = Game.builder().players(playersList).build();
        gameService.resetGame(game);

        verify(playerService, times(2)).updatePlayer(any());
    }

    @Test
    public void playGame() {
        Player player1 = Player.builder().id(0).name(PLAYER_1).build();
        Player player2 = Player.builder().id(1).name(PLAYER_2).build();

        when(playerService.generatePlayer()).thenReturn(player1);
        when(playerService.generatePlayer(anyString())).thenReturn(player2);
        Game game = gameService.newGame(PLAYER_2);
        Game gameResult = gameService.playGame(game.getId());

        assertThat(gameResult.getPlayers()).hasSize(2);
        assertThat(gameResult).isNotNull();
        assertThat(gameResult.getRounds()).isEqualTo(1);
        assertThat(gameResult.getPlayers()[0].getLastMovement()).isEqualTo(ROCK);
        assertThat(gameResult.getGameStats().size()).isEqualTo(3);
        assertThat(gameResult.getGameStats().get(StatsEnum.WINS_PLAYER_1)).isBetween(0, 1);
        assertThat(gameResult.getGameStats().get(StatsEnum.WINS_PLAYER_2)).isBetween(0, 1);
        assertThat(gameResult.getGameStats().get(StatsEnum.DRAWS)).isBetween(0, 1);

    }
}
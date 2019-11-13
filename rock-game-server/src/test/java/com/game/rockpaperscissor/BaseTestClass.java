package com.game.rockpaperscissor;

import com.game.rockpaperscissor.controller.GameController;
import com.game.rockpaperscissor.data.StatsEnum;
import com.game.rockpaperscissor.model.Game;
import com.game.rockpaperscissor.model.GameStats;
import com.game.rockpaperscissor.model.Player;
import com.game.rockpaperscissor.service.GameService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.EnumMap;
import java.util.Map;

import static com.game.rockpaperscissor.data.ChoiceEnum.ROCK;
import static com.game.rockpaperscissor.data.ChoiceEnum.SCISSORS;
import static java.util.Collections.singletonList;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {

    private static final String RETRO = "retro";
    private static final String PLAYER_ROCK = "playerrock";
    private static final String PLAYER_1 = "player1";
    private static final String PLAYER_2 = "player2";
    @Autowired
    private GameController gameController;

    @MockBean
    private GameService gameService;

    @Before
    public void setup() {
        getGameId1();
        postNewGame();
        playGameId1();
        getAllGames();
        getGameStats();
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(gameController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    private void getGameId1() {
        Player player1 = Player.builder().id(1).name(PLAYER_1).lastMovement(ROCK).wins(0).draws(1).build();
        Player player2 = Player.builder().id(2).name(PLAYER_2).lastMovement(ROCK).wins(0).draws(1).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1,0);
        gameStats.put(StatsEnum.WINS_PLAYER_2,0);
        gameStats.put(StatsEnum.DRAWS,1);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.getGame(eq(1))).thenReturn(game);
    }

    private void getAllGames() {
        Player player1 = Player.builder().id(1).name(PLAYER_1).lastMovement(ROCK).wins(0).draws(1).build();
        Player player2 = Player.builder().id(2).name(PLAYER_2).lastMovement(ROCK).wins(0).draws(1).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1, 0);
        gameStats.put(StatsEnum.WINS_PLAYER_2, 0);
        gameStats.put(StatsEnum.DRAWS, 1);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.getGames()).thenReturn(singletonList(game));
    }

    private void postNewGame() {
        Player player1 = Player.builder().id(1).name(PLAYER_ROCK).build();
        Player player2 = Player.builder().id(2).name(RETRO).build();
        Game game = Game.builder().id(1).players(Arrays.array(player1, player2))
                .build();
        when(gameService.newGame(eq(RETRO))).thenReturn(game);
    }

    private void playGameId1() {
        Player player1 = Player.builder().id(1).name(PLAYER_ROCK).lastMovement(ROCK).wins(1).draws(0).build();
        Player player2 = Player.builder().id(2).name(RETRO).lastMovement(SCISSORS).wins(0).draws(0).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1, 1);
        gameStats.put(StatsEnum.WINS_PLAYER_2, 0);
        gameStats.put(StatsEnum.DRAWS, 0);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.playGame(eq(1))).thenReturn(game);
    }

    private void getGameStats() {
        when(gameService.getGamesStats()).thenReturn(of(
        GameStats
                .builder()
                .id(1)
                .stats(of(1, 2, 3, 4, 5))
                .rounds(3)
                .playersNames(of("Tom","rocky"))
                .build(),
        GameStats
                .builder()
                .id(2)
                .stats(of(10, 20, 30, 40, 50))
                .rounds(3)
                .playersNames(of("TomA","rockyA"))
                .build(),
        GameStats
                .builder()
                .id(3)
                .stats(of(100, 200, 300, 400, 500))
                .rounds(3)
                .playersNames(of("TomB","rockyB"))
                .build()
        ));
    }
}

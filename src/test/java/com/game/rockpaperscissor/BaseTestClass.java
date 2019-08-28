package com.game.rockpaperscissor;

import com.game.rockpaperscissor.controller.GameController;
import com.game.rockpaperscissor.data.StatsEnum;
import com.game.rockpaperscissor.model.Game;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {

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
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(gameController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    private void getGameId1() {
        Player player1 = Player.builder().id(1).name("player1").lastMovement(ROCK).rounds(1).wins(0).draws(1).build();
        Player player2 = Player.builder().id(2).name("player2").lastMovement(ROCK).rounds(1).wins(0).draws(1).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1,0);
        gameStats.put(StatsEnum.WINS_PLAYER_2,0);
        gameStats.put(StatsEnum.DRAWS,1);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.getGame(eq(1))).thenReturn(game);
    }

    private void getAllGames() {
        Player player1 = Player.builder().id(1).name("player1").lastMovement(ROCK).rounds(1).wins(0).draws(1).build();
        Player player2 = Player.builder().id(2).name("player2").lastMovement(ROCK).rounds(1).wins(0).draws(1).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1, 0);
        gameStats.put(StatsEnum.WINS_PLAYER_2, 0);
        gameStats.put(StatsEnum.DRAWS, 1);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.getGames()).thenReturn(singletonList(game));
    }

    private void postNewGame() {
        Player player1 = Player.builder().id(1).name("playerrock").build();
        Player player2 = Player.builder().id(2).name("retro").build();
        Game game = Game.builder().id(1).players(Arrays.array(player1, player2))
                .build();
        when(gameService.newGame(eq("retro"))).thenReturn(game);
    }

    private void playGameId1() {
        Player player1 = Player.builder().id(1).name("playerrock").lastMovement(ROCK).rounds(1).wins(1).draws(0).build();
        Player player2 = Player.builder().id(2).name("retro").lastMovement(SCISSORS).rounds(1).wins(0).draws(0).build();
        Map<StatsEnum, Integer> gameStats = new EnumMap<>(StatsEnum.class);
        gameStats.put(StatsEnum.WINS_PLAYER_1, 1);
        gameStats.put(StatsEnum.WINS_PLAYER_2, 0);
        gameStats.put(StatsEnum.DRAWS, 0);
        Game game = Game.builder().id(1).rounds(1).players(Arrays.array(player1, player2))
                .gameStats(gameStats).build();
        when(gameService.playGame(eq(1))).thenReturn(game);
    }
}

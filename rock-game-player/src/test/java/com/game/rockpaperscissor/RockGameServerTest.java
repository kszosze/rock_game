package com.game.rockpaperscissor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.rockpaperscissor.model.Game;
import com.game.rockpaperscissor.service.PlayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.game:contracts:+:stubs:8095")
@ActiveProfiles("test")
public class RockGameServerTest {

    @Autowired
    private PlayService playService;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMappper);
    }

    @Test
    public void should_Create_A_Player() {
      Game game = playService.createPlayer("TestPlayer");
      assertThat(game).isNotNull();
    }

    public void should_Start_A_Game() {
        Game game = playService.startGame(1);
        assertThat(game).isNotNull();
    }

    public void should_Get_A_Game() {
        Game game = playService.getGame(1);
        assertThat(game).isNotNull();
    }

    public void should_Get_All_Game_Stats() {
        List<Game> gameList = playService.getAllGameStats();
        assertThat(gameList).isNotEmpty();
        assertThat(gameList).hasSize(3);
    }

    public void should_Get_All_Game() {
        List<Game> gameList = playService.getAllGame();
        assertThat(gameList).isNotEmpty();
        assertThat(gameList).hasSize(3);
    }
}

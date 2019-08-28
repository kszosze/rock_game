package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.data.ChoiceEnum;
import com.game.rockpaperscissor.data.StatsEnum;
import com.game.rockpaperscissor.model.Game;
import com.game.rockpaperscissor.data.ResultEnum;
import com.game.rockpaperscissor.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.game.rockpaperscissor.data.ChoiceEnum.ROCK;
import static com.game.rockpaperscissor.data.ResultEnum.DRAW;
import static com.game.rockpaperscissor.data.ResultEnum.WIN;

@Service
public class GameService {

    private PlayerService playerService;

    private Map<Integer, Game> gamesMap = new HashMap<>();

    private Random random = new Random();

    public GameService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Player[] generatePlayers(String playerName) {
        Player[] playersList = new Player[2];
        playersList[0] = playerService.generatePlayer();
        playersList[1] = playerService.generatePlayer(playerName);
        return playersList;
    }

    public Game newGame(String playerName) {
        Player[] players = generatePlayers(playerName);
        Game game = Game.builder().id(gamesMap.size() + 1).players(players).build();
        gamesMap.put(game.getId(), game);
        return game;
    }

    public List<Game> getGames() {
        return new ArrayList(gamesMap.values());
    }

    public Game resetGame(Integer gameId){
        Game game = getGame(gameId);

        game.getPlayers()[0].reset();

        playerService.updatePlayer(game.getPlayers()[0]);

        game.getPlayers()[1].reset();
        playerService.updatePlayer(game.getPlayers()[1]);

        return game;
    }

    public Game resetGame(Game game){
        game.getPlayers()[0].reset();

        playerService.updatePlayer(game.getPlayers()[0]);

        game.getPlayers()[1].reset();
        playerService.updatePlayer(game.getPlayers()[1]);

        return game;
    }

    public Game playGame(Integer gameId) {

        Game game = gamesMap.get(gameId);
        Player[] playersList = game.getPlayers();

        playersList[0].setLastMovement(getDefaultMovement());
        playersList[1].setLastMovement(getRandomMovement());

        ResultEnum gameResult = solveGame(playersList[0], playersList[1]);
        game.setLastRoundResult(gameResult);

        if (WIN.equals(gameResult)) {
            playersList[0].incWins();
            game.incStat(StatsEnum.WINS_PLAYER_1);
        } else if (DRAW.equals(gameResult)) {
            playersList[0].indDraws();
            playersList[1].indDraws();
            game.incStat(StatsEnum.DRAWS);
        } else{
            playersList[1].incWins();
            game.incStat(StatsEnum.WINS_PLAYER_2);
        }

        return game;
    }

    private ChoiceEnum getRandomMovement() {
       return ChoiceEnum.values()[random.nextInt(3)];
    }

    private ChoiceEnum getDefaultMovement() {
        return ROCK;
    }

    private ResultEnum solveGame(Player player1, Player player2) {
        ResultEnum result = ResultEnum.LOOSE;
        if (player1.getLastMovement().beat(player2.getLastMovement())) {
            result = WIN;
        } else if (player1.getLastMovement().equals(player2.getLastMovement())) {
            result = DRAW;
        }
        return result;
    }

    public Game getGame(Integer gameId) {
        return gamesMap.get(gameId);
    }
}

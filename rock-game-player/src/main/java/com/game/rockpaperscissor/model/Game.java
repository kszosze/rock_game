package com.game.rockpaperscissor.model;

import com.game.rockpaperscissor.data.ResultEnum;
import com.game.rockpaperscissor.data.StatsEnum;
import lombok.Builder;
import lombok.Data;

import java.util.EnumMap;
import java.util.Map;

@Data
@Builder
public class Game {

    @Builder.Default private Integer id = 0;
    @Builder.Default private Integer rounds = 0;
    private Player[] players;
    private ResultEnum lastRoundResult;
    @Builder.Default private Map<StatsEnum, Integer> gameStats = new EnumMap<StatsEnum, Integer>(StatsEnum.class) {{
        put(StatsEnum.WINS_PLAYER_1, 0);
        put(StatsEnum.WINS_PLAYER_2, 0);
        put(StatsEnum.DRAWS, 0);
    }};

    public void incStat(StatsEnum stats) {
        gameStats.put(stats, gameStats.get(stats)+1);
        this.rounds++;
    }
}

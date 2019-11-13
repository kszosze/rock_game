package com.game.rockpaperscissor.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameStats {

    private Integer id;
    private Integer rounds;
    private List<Integer> stats;
    private List<String> playersNames;
}

package com.game.rockpaperscissor.model;

import com.game.rockpaperscissor.data.ChoiceEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    private Integer id;
    private String name;
    private ChoiceEnum lastMovement;
    @Builder.Default private Integer wins = 0;
    @Builder.Default private Integer draws = 0;
    @Builder.Default private Integer rounds = 0;

    public void setLastMovement(ChoiceEnum choice) {
        this.lastMovement = choice;
        this.rounds++;
    }

    public void incWins() {
        wins++;
    }

    public void indDraws() {
        draws++;
    }

    public void reset() {
        wins = 0;
        draws = 0;
        rounds = 0;
        lastMovement = null;
    }
}

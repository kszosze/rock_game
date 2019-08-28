package com.game.rockpaperscissor.data;

public enum ChoiceEnum {

    ROCK,
    SCISSORS,
    PAPER;

    public boolean beat(ChoiceEnum otherChoice) {
        boolean isBeated = false;
        switch (this) {
            case ROCK: isBeated = otherChoice.equals(SCISSORS); break;
            case SCISSORS: isBeated = otherChoice.equals(PAPER); break;
            case PAPER: isBeated = otherChoice.equals(ROCK); break;
        }
        return isBeated;
    }
}

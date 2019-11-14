package com.xl.spaceship.query.model;

public final class GameWinnerDto {

    private final String won;

    public GameWinnerDto(String won) {
        this.won = won;
    }

    public String getWon() {
        return won;
    }
}

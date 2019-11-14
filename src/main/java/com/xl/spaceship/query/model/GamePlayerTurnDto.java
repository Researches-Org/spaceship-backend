package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GamePlayerTurnDto {

    @JsonProperty("player_turn")
    private final String playerTurnId;

    public GamePlayerTurnDto(String playerTurnId) {
        this.playerTurnId = playerTurnId;
    }

    public String getPlayerTurnId() {
        return playerTurnId;
    }
}

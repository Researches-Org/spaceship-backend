package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GameCreatedDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("game_id")
    private String gameId;

    private String starting;

    public GameCreatedDto() {}

    public GameCreatedDto(String userId, String fullName, String gameId, String starting) {
        this.userId = userId;
        this.fullName = fullName;
        this.gameId = gameId;
        this.starting = starting;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGameId() {
        return gameId;
    }

    public String getStarting() {
        return starting;
    }
}

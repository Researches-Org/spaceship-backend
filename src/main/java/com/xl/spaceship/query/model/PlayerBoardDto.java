package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlayerBoardDto {

    @JsonProperty("user_id")
    private final String userId;

    private final String[] board;

    public PlayerBoardDto(String userId, String[] board) {
        this.userId = userId;
        this.board = board;
    }

    public String getUserId() {
        return userId;
    }

    public String[] getBoard() {
        return board;
    }
}

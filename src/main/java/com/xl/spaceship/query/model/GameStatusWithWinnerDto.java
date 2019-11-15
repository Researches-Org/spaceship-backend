package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

final class GameStatusWithWinnerDto extends GameStatusDto {

    @JsonProperty("game")
    private final GameWinnerDto gameWinnerDto;

    public GameStatusWithWinnerDto(PlayerBoardDto self, PlayerBoardDto opponent, GameWinnerDto gameWinnerDto) {
        super(self, opponent);
        this.gameWinnerDto = gameWinnerDto;
    }

    public GameWinnerDto getGameWinnerDto() {
        return gameWinnerDto;
    }
}

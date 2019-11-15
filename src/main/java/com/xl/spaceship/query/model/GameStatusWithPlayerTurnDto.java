package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

final class GameStatusWithPlayerTurnDto extends GameStatusDto {

    @JsonProperty("game")
    private final GamePlayerTurnDto gamePlayerTurnDto;

    public GameStatusWithPlayerTurnDto(PlayerBoardDto self, PlayerBoardDto opponent, GamePlayerTurnDto gamePlayerTurnDto) {
        super(self, opponent);
        this.gamePlayerTurnDto = gamePlayerTurnDto;
    }

    public GamePlayerTurnDto getGamePlayerTurnDto() {
        return gamePlayerTurnDto;
    }
}

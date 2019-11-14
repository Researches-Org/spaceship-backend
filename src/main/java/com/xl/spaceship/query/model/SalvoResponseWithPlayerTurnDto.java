package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class SalvoResponseWithPlayerTurnDto extends SalvoResponseDto {

    @JsonProperty("game")
    private final GamePlayerTurnDto gamePlayerTurnDto;

    public SalvoResponseWithPlayerTurnDto(GamePlayerTurnDto gamePlayerTurnDto, Map<String, String> salvo) {
        super(salvo);
        this.gamePlayerTurnDto = gamePlayerTurnDto;
    }

    public GamePlayerTurnDto getGamePlayerTurnDto() {
        return gamePlayerTurnDto;
    }
}

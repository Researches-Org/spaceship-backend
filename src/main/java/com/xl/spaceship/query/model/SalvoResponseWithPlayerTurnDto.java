package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

final class SalvoResponseWithPlayerTurnDto extends SalvoResponseDto {

    @JsonProperty("game")
    private GamePlayerTurnDto gamePlayerTurnDto;

    public SalvoResponseWithPlayerTurnDto() {}

    public SalvoResponseWithPlayerTurnDto(GamePlayerTurnDto gamePlayerTurnDto, Map<String, String> salvo) {
        super(salvo);
        this.gamePlayerTurnDto = gamePlayerTurnDto;
    }

    public GamePlayerTurnDto getGamePlayerTurnDto() {
        return gamePlayerTurnDto;
    }

    @Override
    public String getWinnerId() {
        return null;
    }

    @Override
    public String getPlayerTunerId() {
        return gamePlayerTurnDto.getPlayerTurnId();
    }
}

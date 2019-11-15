package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

final class SalvoResponseWithWinnerDto extends SalvoResponseDto {

    @JsonProperty("game")
    private final GameWinnerDto gameWinnerDto;

    public SalvoResponseWithWinnerDto(GameWinnerDto gameWinnerDto, Map<String, String> salvo) {
        super(salvo);
        this.gameWinnerDto = gameWinnerDto;
    }

    public SalvoResponseWithWinnerDto(GameWinnerDto gameWinnerDto, Map<String, String> salvo, boolean gameHadFinished) {
        super(salvo, gameHadFinished);
        this.gameWinnerDto = gameWinnerDto;
    }

    public GameWinnerDto getGameWinnerDto() {
        return gameWinnerDto;
    }

    @Override
    public String getWinnerId() {
        return gameWinnerDto.getWon();
    }

    @Override
    public String getPlayerTunerId() {
        return null;
    }
}



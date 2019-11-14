package com.xl.spaceship.query.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class SalvoResponseWithWinnerDto extends SalvoResponseDto {

    @JsonProperty("game")
    private final GameWinnerDto gameWinnerDto;

    public SalvoResponseWithWinnerDto(GameWinnerDto gameWinnerDto, Map<String, String> salvo) {
        super(salvo);
        this.gameWinnerDto = gameWinnerDto;
    }

    public GameWinnerDto getGameWinnerDto() {
        return gameWinnerDto;
    }

}



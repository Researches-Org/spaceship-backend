package com.xl.spaceship.query.model;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class SalvoResponseDto {

    private final Map<String, String> salvo;

    public SalvoResponseDto(Map<String, String> salvo) {
        this.salvo = Maps.newLinkedHashMap(salvo);
    }

    public static SalvoResponseDto withWinner(String winnerId, Map<String, String> salvoResponse) {
        return new SalvoResponseWithWinnerDto(new GameWinnerDto(winnerId), salvoResponse);
    }

    public static SalvoResponseDto withPlayer(String playerId, Map<String, String> salvoResponse) {
        return new SalvoResponseWithPlayerTurnDto(new GamePlayerTurnDto(playerId), salvoResponse);
    }

    public Map<String, String> getSalvo() {
        return salvo;
    }
}

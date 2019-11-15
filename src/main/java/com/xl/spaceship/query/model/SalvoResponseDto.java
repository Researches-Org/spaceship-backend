package com.xl.spaceship.query.model;

import com.google.common.collect.Maps;

import java.util.Map;

public abstract class SalvoResponseDto {

    private final Map<String, String> salvo;

    private boolean gameHadFinished;

    public SalvoResponseDto(Map<String, String> salvo) {
        this.salvo = Maps.newLinkedHashMap(salvo);
    }

    public SalvoResponseDto(Map<String, String> salvo, boolean gameHadFinished) {
        this.salvo = Maps.newLinkedHashMap(salvo);
        this.gameHadFinished = gameHadFinished;
    }

    public abstract String getWinnerId();

    public abstract String getPlayerTunerId();

    public static SalvoResponseDto withWinner(String winnerId, Map<String, String> salvoResponse) {
        return new SalvoResponseWithWinnerDto(new GameWinnerDto(winnerId), salvoResponse);
    }

    public static SalvoResponseDto withWinnerWhenGameHadFinised(String winnerId, Map<String, String> salvoResponse) {
        return new SalvoResponseWithWinnerDto(new GameWinnerDto(winnerId), salvoResponse, true);
    }

    public static SalvoResponseDto withPlayer(String playerId, Map<String, String> salvoResponse) {
        return new SalvoResponseWithPlayerTurnDto(new GamePlayerTurnDto(playerId), salvoResponse);
    }

    public Map<String, String> getSalvo() {
        return salvo;
    }

    public boolean isGameHadFinished() {
        return gameHadFinished;
    }
}

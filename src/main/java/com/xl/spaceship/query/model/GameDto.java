package com.xl.spaceship.query.model;

public final class GameDto {

    private final String gameId;

    private final String selfId;

    private final String opponentId;

    private final boolean autopilot;

    private final boolean finished;

    private final String playerTurnId;

    private final String winnerId;

    private final int totalSpaceships;

    public GameDto(String gameId,
                   String selfId,
                   String opponentId,
                   boolean autopilot,
                   boolean finished,
                   String playerTurnId,
                   String winnerId,
                   int totalSpaceships) {
        this.gameId = gameId;
        this.selfId = selfId;
        this.opponentId = opponentId;
        this.autopilot = autopilot;
        this.finished = finished;
        this.playerTurnId = playerTurnId;
        this.winnerId = winnerId;
        this.totalSpaceships = totalSpaceships;
    }

    public String getGameId() {
        return gameId;
    }

    public String getSelfId() {
        return selfId;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public boolean isAutopilot() {
        return autopilot;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getPlayerTurnId() {
        return playerTurnId;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public int getTotalSpaceships() {
        return totalSpaceships;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", selfId='" + selfId + '\'' +
                ", opponentId='" + opponentId + '\'' +
                ", autopilot=" + autopilot +
                ", finished=" + finished +
                ", playerTurnId='" + playerTurnId + '\'' +
                ", winnerId='" + winnerId + '\'' +
                ", totalSpaceships=" + totalSpaceships +
                '}';
    }
}

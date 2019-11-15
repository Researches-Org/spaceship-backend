package com.xl.spaceship.domain.model;

import java.util.Objects;

public final class SalvoReceivedEvent implements DomainEvent {

    private final GameId gameId;

    private SalvoReceivedEvent(GameId gameId) {
        this.gameId = Objects.requireNonNull(gameId);
    }

    public static SalvoReceivedEvent of(GameId gameId) {
        return new SalvoReceivedEvent(gameId);
    }

    public GameId getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalvoReceivedEvent that = (SalvoReceivedEvent) o;
        return gameId.equals(that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }

    @Override
    public String toString() {
        return "SalvoReceivedEvent{" +
                "gameId=" + gameId +
                '}';
    }
}

package com.xl.spaceship.domain.model;

import java.util.UUID;

public final class GameId extends Id {

    public GameId(UUID value) {
        super(value);
    }

    @Override
    public String toString() {
        return "GameId{" +
                "value=" + value +
                '}';
    }
}

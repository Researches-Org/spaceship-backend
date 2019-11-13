package com.xl.spaceship.domain.model;

import java.util.UUID;

public final class PlayerId extends Id {

    public PlayerId(UUID value) {
        super(value);
    }

    @Override
    public String toString() {
        return "PlayerId{" +
                "value=" + value +
                '}';
    }

    public static PlayerId random() {
        return new PlayerId(UUID.randomUUID());
    }

    public static PlayerId of(String id) {
        return new PlayerId(UUID.fromString(id));
    }
}

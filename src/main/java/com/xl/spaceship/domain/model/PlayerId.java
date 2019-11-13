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
}

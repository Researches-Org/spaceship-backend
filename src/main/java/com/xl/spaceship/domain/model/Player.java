package com.xl.spaceship.domain.model;

import java.util.Objects;

public final class Player {

    private final PlayerId id;

    private PlayerName name;

    public Player(PlayerId id, PlayerName name) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);

        this.id = id;
        this.name = name;
    }

    public PlayerId getId() {
        return id;
    }
}

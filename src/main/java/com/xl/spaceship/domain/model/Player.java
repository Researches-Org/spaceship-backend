package com.xl.spaceship.domain.model;

import java.util.Objects;

public final class Player {

    private final PlayerId id;

    private final PlayerName name;

    private final SpaceshipProtocol spaceshipProtocol;

    public Player(PlayerId id, PlayerName name, SpaceshipProtocol spaceshipProtocol) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.spaceshipProtocol = Objects.requireNonNull(spaceshipProtocol);
    }

    public PlayerId getId() {
        return id;
    }

    public PlayerName getName() {
        return name;
    }

    public SpaceshipProtocol getSpaceshipProtocol() {
        return spaceshipProtocol;
    }
}

package com.xl.spaceship.domain.model;

public final class PlayerName extends Name {

    public PlayerName(String value) {
        super(value, 100);
    }

    @Override
    public String toString() {
        return "PlayerName{" +
                "value='" + value + '\'' +
                '}';
    }
}

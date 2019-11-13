package com.xl.spaceship.domain.model;

public final class PlayerName extends Name {

    public PlayerName(String value) {
        super(value, 100);
    }

    public static PlayerName of(String value) {
        return new PlayerName(value);
    }

    @Override
    public String toString() {
        return "PlayerName{" +
                "value='" + value + '\'' +
                '}';
    }
}

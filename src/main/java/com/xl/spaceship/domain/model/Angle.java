package com.xl.spaceship.domain.model;

import java.util.Arrays;

public final class Angle extends AbstractSpaceship {

    private static final char[][] DEFAULT = {
            {'*', '.', '.'},
            {'*', '.', '.'},
            {'*', '.', '.'},
            {'*', '*', '*'},
    };

    public Angle() {
        super(DEFAULT);
    }

    private Angle(char[][] value) {
        super(value);
    }

    @Override
    protected Spaceship create(char[][] value) {
        return new Angle(value);
    }

    @Override
    public String toString() {
        return "Angle{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}

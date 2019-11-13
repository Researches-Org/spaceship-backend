package com.xl.spaceship.domain.model;

import java.util.Arrays;

public final class Winger extends AbstractSpaceship {

    private static final char[][] DEFAULT = {
            {'*', '.', '*'},
            {'*', '.', '*'},
            {'.', '*', '.'},
            {'*', '.', '*'},
            {'*', '.', '*'},
    };

    public Winger() {
        super(DEFAULT);
    }

    private Winger(char[][] value) {
        super(value);
    }

    @Override
    protected Spaceship create(char[][] value) {
        return new Winger(value);
    }

    @Override
    public String toString() {
        return "Winger{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}

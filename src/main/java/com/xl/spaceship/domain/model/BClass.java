package com.xl.spaceship.domain.model;

import java.util.Arrays;

public final class BClass extends AbstractSpaceship {

    private static final char[][] DEFAULT = {
            {'*', '*', '.'},
            {'*', '.', '*'},
            {'*', '*', '.'},
            {'*', '.', '*'},
            {'*', '*', '.'},
    };

    public BClass() {
        super(DEFAULT);
    }

    private BClass(char[][] value) {
        super(value);
    }

    @Override
    protected Spaceship create(char[][] value) {
        return new BClass(value);
    }

    @Override
    public String toString() {
        return "BClass{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}

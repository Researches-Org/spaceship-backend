package com.xl.spaceship.domain.model;

import java.util.Arrays;

public final class AClass extends AbstractSpaceship {

    private static final char[][] DEFAULT = {
            {'.', '*', '.'},
            {'*', '.', '*'},
            {'*', '*', '*'},
            {'*', '.', '*'},
    };

    public AClass() {
        super(DEFAULT);
    }

    private AClass(char[][] value) {
        super(value);
    }

    @Override
    protected Spaceship create(char[][] value) {
        return new AClass(value);
    }

    @Override
    public String toString() {
        return "AClass{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}

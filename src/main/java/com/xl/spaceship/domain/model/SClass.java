package com.xl.spaceship.domain.model;

import java.util.Arrays;

public final class SClass extends AbstractSpaceship {

    private static final char[][] DEFAULT = {
            {'.', '*', '*', '.'},
            {'*', '.', '.', '.'},
            {'.', '*', '*', '.'},
            {'.', '.', '.', '*'},
            {'.', '*', '*', '.'},
    };

    public SClass() {
        super(DEFAULT);
    }

    private SClass(char[][] value) {
        super(value);
    }

    @Override
    protected Spaceship create(char[][] value) {
        return new SClass(value);
    }

    @Override
    public String toString() {
        return "SClass{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}

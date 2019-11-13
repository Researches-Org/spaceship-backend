package com.xl.spaceship.domain.model;

import com.xl.spaceship.util.MatrixUtil;

import java.util.Arrays;

abstract class AbstractSpaceship implements Spaceship {

    protected char[][] value;

    AbstractSpaceship(char[][] value) {
        this.value = value;
    }

    protected abstract Spaceship create(char[][] value);

    @Override
    public Spaceship rotateLeft() {
        return create(MatrixUtil.rotateLeft(value));
    }

    @Override
    public Spaceship rotateRight() {
        return create(MatrixUtil.transpose(value));
    }

    @Override
    public Dimension getDimension() {
        return Dimension.of(value.length, value[0].length);
    }

    @Override
    public char get(int i, int j) {
        return value[i][j];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSpaceship that = (AbstractSpaceship) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}

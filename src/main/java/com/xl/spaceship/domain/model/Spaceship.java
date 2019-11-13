package com.xl.spaceship.domain.model;

public interface Spaceship {

    Spaceship rotateLeft();

    Spaceship rotateRight();

    Dimension getDimension();

    char get(int i, int j);

}

package com.xl.spaceship.domain.model;

public final class Dimension {

    private final int height;

    private final int width;

    private Dimension(int height, int width)  {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static Dimension of(int height, int width) {
        return new Dimension(height, width);
    }
}

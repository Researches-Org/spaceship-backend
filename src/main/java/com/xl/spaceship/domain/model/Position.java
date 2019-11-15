package com.xl.spaceship.domain.model;

import java.util.Objects;

public final class Position {

    private final int row;

    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position fromShot(String shot) {
        String[] shotSplit = shot.split("x");

        return new Position(
                Integer.valueOf(shotSplit[0], 16),
                Integer.valueOf(shotSplit[1], 16));
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public String toShot() {
        return String.format("%sx%s", Integer.toHexString(row).toUpperCase(), Integer.toHexString(column).toUpperCase());
    }
}

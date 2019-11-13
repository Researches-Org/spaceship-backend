package com.xl.spaceship.domain.model;

import com.xl.spaceship.util.MatrixUtil;
import com.xl.spaceship.util.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Board {

    private static final int SIZE = 16;

    private static final int MAX_ROTATIONS = 3;

    private static final char EMPTY = '.';

    private final char[][] value;

    private Board() {
        value = MatrixUtil.newMatrixWith(SIZE, EMPTY);
    }

    private Board addAtRandom(Spaceship spaceship) {
        if (!MatrixUtil.hasFreeArea(spaceship.getDimension(), value, EMPTY)) {
            throw new IllegalArgumentException("Cannot add spaceship, there is no free area");
        }

        Position position = RandomUtil.chooseValidPositionAtRandom(spaceship.getDimension(), value, EMPTY);

        return addAtPosition(spaceship, position);
    }

    private Board addAtPosition(Spaceship spaceship, Position position) {
        int height = spaceship.getDimension().getHeight();
        int width = spaceship.getDimension().getWidth();

        int startRow = position.getRow();
        int startColumn = position.getColumn();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                value[i + startRow][j + startColumn] = spaceship.get(i, j);
            }
        }

        return this;
    }

    public static Board empty() {
        return new Board();
    }

    public static Board random() {
        List<Spaceship> spaceships = RandomUtil.shuffle(SpaceShips.all());

        Board result = spaceships.stream().reduce(Board.empty(),
                (board, spaceship) -> board.addAtRandom(RandomUtil.rotateAtMax(spaceship, MAX_ROTATIONS)),
                (board1, board2) -> board2);

        return result;
    }


    @Override
    public String toString() {
        return "Board{"
                + "value="
                + System.lineSeparator()
                + Arrays.stream(value).map(row -> Arrays.toString(row)).collect(Collectors.joining(System.lineSeparator()))
                + '}';
    }
}

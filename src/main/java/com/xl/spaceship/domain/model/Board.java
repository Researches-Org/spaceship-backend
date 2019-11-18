package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.util.MatrixUtil;
import com.xl.spaceship.util.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Board {

    protected static final int SIZE = 16;

    private static final int MAX_ROTATIONS = 3;

    private static final char EMPTY = '.';

    private static final char SHIP = '*';

    private static final char MISSED_SHOT = '-';

    protected static final char HIT = 'X';

    private final char[][] value;

    private final Map<Position, Spaceship> spaceships;

    private Board() {
        value = MatrixUtil.newMatrixWith(SIZE, EMPTY);
        spaceships = Maps.newHashMap();
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
                char c = spaceship.get(i, j);

                int row = i + startRow;
                int column = j + startColumn;

                value[row][column] = c;

                if (c == SHIP) {
                    spaceships.put(new Position(row, column), spaceship);
                }
            }
        }

        return this;
    }

    protected static Board empty() {
        return new Board();
    }

    protected static Board random() {
        List<Spaceship> spaceships = RandomUtil.shuffle(Spaceships.all());

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

    public String[] toStringArray() {
        return Arrays.stream(value)
                .map(row -> new String(row))
                .collect(Collectors.toList())
                .toArray(new String[]{});
    }

    protected boolean hasSpaceship() {
       return !spaceships.isEmpty();
    }

    protected Map<String, String> receiveSalvo(SalvoCmd cmd) {
        List<Shot> response = Arrays.stream(cmd.getSalvo())
                .map(shot -> Position.fromShot(shot))
                .map(position -> receiveShotAtPosition(position))
                .collect(Collectors.toList());

        Map<String, String> map = Maps.newLinkedHashMap();

        IntStream.range(0, response.size())
                .forEach(i -> {
                    Shot s = response.get(i);
                    map.put(s.getShot(), s.getResult());
                });

        return map;
    }

    private Shot receiveShotAtPosition(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        char currentValue = value[row][column];

        if (currentValue == SHIP) {
            Spaceship spaceship = spaceships.remove(position);

            value[row][column] = HIT;

            if (spaceships.containsValue(spaceship)) {
                return Shot.hit(position);
            } else {
                return Shot.kill(position);
            }
        } else if (currentValue == EMPTY) {
            value[row][column] = MISSED_SHOT;
        }

        return Shot.miss(position);

    }

    protected void update(Shot shot) {
        Position position = Position.fromShot(shot.getShot());

        int row = position.getRow();
        int column = position.getColumn();

        char c = value[row][column];

        if (shot.isHit() || shot.isKill()) {
            value[row][column] = HIT;
        }
        else if (shot.isMiss() && c == EMPTY) {
            value[row][column] = MISSED_SHOT;
        }

    }

    public int getTotalSpaceships() {
        return Sets.newHashSet(spaceships.values()).size();
    }

    protected SalvoCmd generateRandomSalvo(int shots) {
        List<Position> positions = RandomUtil.getEmptyPositionsAtRandom(value, shots, EMPTY);

        String[] salvo = positions.stream()
                .map(p -> p.toShot())
                .collect(Collectors.toList())
                .toArray(new String[] {});

        return new SalvoCmd(salvo);
    }
}

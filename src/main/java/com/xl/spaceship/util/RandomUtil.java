package com.xl.spaceship.util;

import com.google.common.collect.Lists;
import com.xl.spaceship.domain.model.Dimension;
import com.xl.spaceship.domain.model.Position;
import com.xl.spaceship.domain.model.Spaceship;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class RandomUtil {

    private static final Random RANDOM = new Random();

    private RandomUtil() {}

    public static <T> T chooseAtRandom(T[] objects) {
        int randomIndex = RANDOM.nextInt(objects.length);

        return objects[randomIndex];
    }

    public static <T> List<T> shuffle(T[] objects) {
        int n = objects.length;

        List<Integer> indexes = IntStream.range(0, n)
                .boxed()
                .collect(Collectors.toList());

        List<T> result = Lists.newArrayList();

        while (result.size() < n) {
            int r = RANDOM.nextInt(indexes.size());

            result.add(objects[indexes.get(r)]);

            indexes.remove(r);
        }

        return result;
    }

    public static Position chooseValidPositionAtRandom(Dimension dimension, char[][] value, char empty) {

        List<Position> emptyPositions = MatrixUtil.getEmptyPositions(dimension, value, empty);

        while (!emptyPositions.isEmpty()) {
            int randomIndex = RANDOM.nextInt(emptyPositions.size());

            Position emptyPosition = emptyPositions.get(randomIndex);

            if (MatrixUtil.isFreeArea(dimension, value, empty, emptyPosition.getRow(), emptyPosition.getColumn())) {
                return emptyPosition;
            }

            emptyPositions.remove(randomIndex);
        }

        throw new IllegalArgumentException("There is no free area");

    }

    public static Spaceship rotateAtMax(Spaceship spaceship, int maxRotations) {
        int rotations = RANDOM.nextInt(maxRotations + 1);

        Spaceship result = IntStream.rangeClosed(1, rotations)
                .boxed()
                .reduce(spaceship, (s, i) -> s.rotateRight(), ((s1, s2) -> s2));

        return result;
    }

    public static List<Position> getEmptyPositionsAtRandom(char[][] value, int numOfPositions, char empty) {
        List<Position> emptyPositions = MatrixUtil.getEmptyPositions(value, empty);

        if (emptyPositions.size() <= numOfPositions) {
            return emptyPositions;
        }

        List<Position> emptyPositionsAtRandom = Lists.newArrayList();

        while (emptyPositionsAtRandom.size() < numOfPositions) {
            int randomIndex = RANDOM.nextInt(emptyPositions.size());

            Position emptyPosition = emptyPositions.get(randomIndex);

            emptyPositionsAtRandom.add(emptyPosition);

            emptyPositions.remove(randomIndex);
        }

        return emptyPositionsAtRandom;
    }
}

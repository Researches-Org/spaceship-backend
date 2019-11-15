package com.xl.spaceship.util;

import com.google.common.collect.Lists;
import com.xl.spaceship.domain.model.Dimension;
import com.xl.spaceship.domain.model.Position;

import java.util.Arrays;
import java.util.List;

public final class MatrixUtil {

    private MatrixUtil() {}

    public static char[][] transpose(char[][] value) {
        int m = value.length;
        int n = value[0].length;

        char[][] transpose = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = value[i][j];
            }
        }

        return transpose;
    }

    public static char[][] rotateLeft(char[][] value) {
        int m = value.length;
        int n = value[0].length;

        char[][] leftRotation = new char[n][m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                leftRotation[n - 1 - i][j] = value[j][i];
            }
        }

        return leftRotation;
    }

    public static boolean hasFreeArea(Dimension dimension, char[][] value, char empty) {
        int height = dimension.getHeight();
        int width = dimension.getWidth();
        int m = value.length;
        int n = value[0].length;

        for (int i = 0; i < m - height + 1; i++) {
            for (int j = 0; j < n - width + 1; j++) {

                if (isFreeArea(dimension, value, empty, i, j)) {
                    return true;
                }

            }
        }

        return false;
    }

    public static boolean isFreeArea(Dimension dimension, char[][] value, char empty, int row, int column) {
        int height = dimension.getHeight();
        int width = dimension.getWidth();

        for (int i = row; i < row + height; i++) {
            for (int j = column; j < column + width; j++ ) {
                if (value[i][j] != empty) {
                    return false;
                }
            }
        }

        return true;
    }

    public static List<Position> getEmptyPositions(Dimension dimension, char[][] value, char empty) {
        int m = value.length - dimension.getHeight() + 1;
        int n = value[0].length - dimension.getWidth() + 1;

        List<Position> emptyPositions = Lists.newArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (value[i][j] == empty) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }

        return emptyPositions;
    }

    public static List<Position> getEmptyPositions(char[][] value, char empty) {
        int m = value.length;
        int n = value[0].length;

        List<Position> emptyPositions = Lists.newArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (value[i][j] == empty) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }

        return emptyPositions;
    }

    public static void fill(char[][] value, char c) {
        int m = value.length;

        for (int i = 0; i < m; i++) {
            Arrays.fill(value[i], c);
        }
    }

    public static char[][] newMatrixWith(int size, char c) {
        char[][] matrix = new char[size][size];

        fill(matrix, c);

        return matrix;
    }
}

package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testRandom() {
        Board board = Board.random();

        System.out.println(board);
    }
}

package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void testToShot() {
        // set up
        Position position = new Position(13, 10);

        // SUT
        String salvo = position.toShot();

        // assert
        Assertions.assertEquals("DxA", salvo);

    }

    @Test
    public void testFromShot() {
        // set up
        Position expected = new Position(13, 10);

        // SUT
        Position position = Position.fromShot("DxA");

        // assert
        Assertions.assertEquals(expected, position);

    }

}

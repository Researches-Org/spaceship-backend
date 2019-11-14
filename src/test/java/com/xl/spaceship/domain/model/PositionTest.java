package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void testToSalvo() {
        // set up
        Position position = new Position(13, 10);

        // SUT
        String salvo = position.toSalvo();

        // assert
        Assertions.assertEquals("DxA", salvo);

    }

    @Test
    public void testFromSalvo() {
        // set up
        Position expected = new Position(13, 10);

        // SUT
        Position position = Position.fromSalvo("DxA");

        // assert
        Assertions.assertEquals(expected, position);


    }

}

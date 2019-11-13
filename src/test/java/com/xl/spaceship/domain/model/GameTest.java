package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameTest {

    @Test
    public void testConstructorWithNullIdShouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new Game(null));
    }

    @Test
    public void testConstructorWithNonNullIdGetShouldReturnId() {
        // set up
        GameId gameId = new GameId(UUID.randomUUID());

        // SUT
        Game game = new Game(gameId);

        // assert
        Assertions.assertEquals(gameId, game.getId());
    }

}

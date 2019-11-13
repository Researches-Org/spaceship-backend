package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameIdTest {

    @Test
    public void testConstructorWithNullIdShouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new GameId(null));
    }

    @Test
    public void testConstructorWithNonNullIdGetShouldReturnId() {
        // set up
        UUID uuid = UUID.randomUUID();

        // System Under Test (SUT)
        Id gameId = new GameId(uuid);

        // assert
        Assertions.assertEquals(uuid, gameId.getValue());
    }

    @Test
    public void testEqualsAndHashCode() {
        // set up
        UUID uuid = UUID.randomUUID();

        // SUT
        Id gameId = new GameId(uuid);
        Id sameGameId = new GameId(uuid);

        // assert
        Assertions.assertEquals(gameId, sameGameId);
        Assertions.assertEquals(gameId.hashCode(), sameGameId.hashCode());
    }

    @Test
    public void testNotEquals() {
        // set up
        UUID uuid = UUID.randomUUID();
        UUID otherUuid = UUID.randomUUID();

        // SUT
        Id gameId = new GameId(uuid);
        Id otherGameId = new GameId(otherUuid);

        // assert
        Assertions.assertNotEquals(gameId, otherGameId);
    }

    @Test
    public void testToString() {
        // set up
        UUID uuid = UUID.randomUUID();

        // SUT
        Id gameId = new GameId(uuid);

        // assert
        Assertions.assertEquals(String.format("GameId{value=%s}", uuid.toString()), gameId.toString());
    }

}

package com.xl.spaceship.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GameTest {

    @Test
    public void testConstructorWithNullIdShouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new Game(null, null, null, null, null));
    }

    @Test
    public void testConstructorWithNonNullIdGetShouldReturnId() {
        // set up
        GameId gameId = new GameId(UUID.randomUUID());
        PlayerId self = new PlayerId(UUID.randomUUID());
        PlayerId opponent = new PlayerId(UUID.randomUUID());
        SpaceshipProtocol selfSpaceshipProtocol = SpaceshipProtocol.of("host", 9090);
        SpaceshipProtocol opponentSpaceshipProtocol = SpaceshipProtocol.of("host", 8080);

        // SUT
        Game game = new Game(gameId, self, selfSpaceshipProtocol, opponent, opponentSpaceshipProtocol);

        // assert
        Assertions.assertEquals(gameId, game.getId());
    }

}

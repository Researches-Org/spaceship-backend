package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameTest {

    private GameId gameId = GameId.random();

    private PlayerId selfId = PlayerId.random();

    private PlayerId opponentId = PlayerId.random();

    private SpaceshipProtocol selfSp = SpaceshipProtocol.of("", 1);

    private SpaceshipProtocol opponentSp = SpaceshipProtocol.of("", 2);

    @Test
    public void testConstructorWithNullShouldThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new Game(null, selfId, selfSp, opponentId, opponentSp));
        Assertions.assertThrows(NullPointerException.class, () -> new Game(gameId, null, selfSp, opponentId, opponentSp));
        Assertions.assertThrows(NullPointerException.class, () -> new Game(gameId, selfId, null, opponentId, opponentSp));
        Assertions.assertThrows(NullPointerException.class, () -> new Game(gameId, selfId, selfSp, null, opponentSp));
        Assertions.assertThrows(NullPointerException.class, () -> new Game(gameId, selfId, selfSp, opponentId, null));
    }

    @Test
    public void testConstructorWithNonNullGetShouldReturn() {
        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp);

        // assert
        Assertions.assertEquals(gameId, game.getId());
        Assertions.assertEquals(selfId, game.getSelf());
        Assertions.assertEquals(opponentId, game.getOpponent());
        Assertions.assertEquals(opponentSp, game.getOpponentSpaceshipProtocol());
        Assertions.assertTrue(game.getPlayerTurn().equals(selfId) || game.getPlayerTurn().equals(opponentId));
        Assertions.assertNull(game.getWinner());
        Assertions.assertFalse(game.hasFinished());
        Assertions.assertFalse(game.isAutopilot());
    }

    @Test
    public void testReceiveSalvoWithSuccess() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[]{"0x0"});

        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp, opponentId);
        SalvoResponseDto salvoResponseDto = game.receiveSalvo(salvoCmd);

        // assert
        Assertions.assertEquals(selfId, game.getPlayerTurn());
        Assertions.assertNull(game.getWinner());
        Assertions.assertFalse(game.isAutopilot());
        Assertions.assertFalse(game.hasFinished());
        Assertions.assertNull(salvoResponseDto.getWinnerId());
        Assertions.assertEquals(game.getPlayerTurn().getValue().toString(), salvoResponseDto.getPlayerTunerId());
        Assertions.assertEquals(1, salvoResponseDto.getSalvo().keySet().size());
        Assertions.assertEquals(Sets.newHashSet("0x0"), salvoResponseDto.getSalvo().keySet());
        Assertions.assertTrue(Shot.validResults().contains(salvoResponseDto.getSalvo().get("0x0")));

    }

    @Test
    public void testReceiveSalvoWhenYourTurnShouldThrowIllegalArgumentException() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[]{"0x0"});

        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp, selfId);

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> game.receiveSalvo(salvoCmd));

    }

    @Test
    public void testValidateSendSalvoCmdWithSuccess() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[]{"0x0"});

        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp, selfId);

        // assert
        Assertions.assertDoesNotThrow(() -> game.validateSendSalvoCmd(salvoCmd));

    }

    @Test
    public void testValidateSendSalvoCmdWithMoreShotsThanSpaceshipsShouldThrowIllegalArgumentException() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[]{"0x0", "0x1", "0x2", "0x3", "0x4", "0x5"});

        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp, selfId);

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> game.validateSendSalvoCmd(salvoCmd));

    }

    @Test
    public void testUpdateOpponentBoardWithSuccess() {
        // set up
        Map<String, String> salvo = Maps.newHashMap();
        salvo.put("0x0", "hit");
        SalvoResponseDto salvoResponseDto = SalvoResponseDto.withPlayer(opponentId.getValue().toString(), salvo);

        // SUT
        Game game = new Game(gameId, selfId, selfSp, opponentId, opponentSp, selfId);
        game.updateOpponentBoard(salvoResponseDto);

        Board opponentBoard = game.getBoard(opponentId);
        String[] rows = opponentBoard.toStringArray();

        // assert
        Assertions.assertEquals(Board.HIT, rows[0].charAt(0));

    }

}

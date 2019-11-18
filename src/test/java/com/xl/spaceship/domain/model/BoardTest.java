package com.xl.spaceship.domain.model;

import com.xl.spaceship.application.command.SalvoCmd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BoardTest {

    @Test
    public void testRandomShouldAddAllSpaceships() {

        // System Under Test (SUT)
        Board board = Board.random();

        // asserts
        Assertions.assertEquals(Spaceships.all().length, board.getTotalSpaceships());
        Assertions.assertTrue(board.hasSpaceship());
    }

    @Test
    public void testReceiveSalvoShouldReturnSalvoResponseWithValidResult() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[] {"0x0"});

        // SUT
        Board board = Board.random();
        Map<String, String> salvoResponse = board.receiveSalvo(salvoCmd);

        // asserts
        Assertions.assertEquals(1, salvoResponse.size());
        Assertions.assertTrue(salvoResponse.containsKey("0x0"));
        Assertions.assertTrue(Shot.validResults().contains(salvoResponse.get("0x0")));
    }

    @Test
    public void testReceiveSalvoWithInvalidPositionShouldThrowIllegalArgumentException() {
        // set up
        SalvoCmd salvoCmd = new SalvoCmd(new String[] {"0x-1"});

        // SUT
        Board board = Board.random();

        // assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.receiveSalvo(salvoCmd));

    }

    @Test
    public void testUpdateWithShouldChangeBoardValueAtPosition() {
        // set up
        Shot hit = Shot.hit(new Position(0, 0));

        // SUT
        Board board = Board.random();
        board.update(hit);
        String[] rows = board.toStringArray();

        // asserts
        Assertions.assertEquals(Board.HIT, rows[0].charAt(0));
    }

    @Test
    public void testGenerateRandomSalvo() {
        // set up
        int shots = 1;

        // SUT
        Board board = Board.random();
        SalvoCmd salvoCmd = board.generateRandomSalvo(shots);
        String[] salvo = salvoCmd.getSalvo();

        // assert
        Assertions.assertEquals(1, salvo.length);

        String[] salvoSplit = salvo[0].split("x");

        Assertions.assertTrue(Integer.parseInt(salvoSplit[0], 16) < Board.SIZE);
        Assertions.assertTrue(Integer.parseInt(salvoSplit[1], 16) < Board.SIZE);
    }
}

package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.query.model.SalvoResponseDto;
import com.xl.spaceship.util.RandomUtil;

import java.util.Map;
import java.util.Objects;

public final class Game {

    private final GameId id;

    private final PlayerId self;

    private final PlayerId opponent;

    private final Map<PlayerId, SpaceshipProtocol> spaceshipProtocolMap;

    private final Map<PlayerId, Board> boards;

    private PlayerId playerTurn;

    private PlayerId winner;

    private boolean autopilot;

    public Game(GameId id,
                PlayerId self,
                SpaceshipProtocol selfSpaceshipProtocol,
                PlayerId opponent,
                SpaceshipProtocol opponentSpaceshipProtocol) {
        this.id = Objects.requireNonNull(id);
        this.self = Objects.requireNonNull(self);
        this.opponent = Objects.requireNonNull(opponent);

        Objects.requireNonNull(selfSpaceshipProtocol);
        Objects.requireNonNull(opponentSpaceshipProtocol);
        this.spaceshipProtocolMap = Maps.newHashMap();
        this.spaceshipProtocolMap.put(self, selfSpaceshipProtocol);
        this.spaceshipProtocolMap.put(opponent, opponentSpaceshipProtocol);

        this.boards = Maps.newHashMap();
        this.boards.put(self, Board.random());
        this.boards.put(opponent, Board.empty());
        this.playerTurn = RandomUtil.chooseAtRandom(new PlayerId[] { self, opponent });
    }

    public GameId getId() {
        return id;
    }

    public PlayerId getSelf() {
        return self;
    }

    public PlayerId getOpponent() {
        return opponent;
    }

    public PlayerId getPlayerTurn() {
        return playerTurn;
    }

    public PlayerId getWinner() {
        return winner;
    }

    public SpaceshipProtocol getOpponentSpaceshipProtocol() {
        return spaceshipProtocolMap.get(opponent);
    }

    public Board getBoard(PlayerId playerId) {
        return boards.get(playerId);
    }

    public SalvoResponseDto receiveSalvo(SalvoCmd cmd) {
        if (playerTurn.equals(self)) {
            throw new IllegalArgumentException("Cannot receive a salvo, it is your turn to send a salvo");
        }

        if (hasFinished()) {
            return SalvoResponseDto.withWinnerWhenGameHadFinised(winner.getValue().toString(), Shot.misses(cmd.getSalvo()));
        }

        Board selfBoard = getBoard(self);

        Map<String, String> salvoResponse = selfBoard.receiveSalvo(cmd);

        if (selfBoard.hasSpaceship()) {
            playerTurn = self;
            return SalvoResponseDto.withPlayer(self.getValue().toString(), salvoResponse);
        }

        winner = opponent;

        return SalvoResponseDto.withWinner(winner.getValue().toString(), salvoResponse);
    }

    public void updateOpponentBoard(SalvoResponseDto salvoResponseDto) {
        if (salvoResponseDto.getWinnerId() != null) {
            winner = PlayerId.of(salvoResponseDto.getWinnerId());
            disableAutopilot();
        } else {
            playerTurn = PlayerId.of(salvoResponseDto.getPlayerTunerId());
        }

        Board opponentBoard = boards.get(opponent);

        salvoResponseDto.getSalvo().entrySet().stream()
        .forEach(e -> {
            String shot = e.getKey();
            String result = e.getValue();

            Shot s = Shot.of(shot, result);

            opponentBoard.update(s);
        });
    }

    public boolean isAutopilot() {
        return autopilot;
    }

    public void enableAutopilot() {
        autopilot = true;
    }

    private void disableAutopilot() {
        autopilot = false;
    }

    public boolean hasFinished() {
        return winner != null;
    }

    public SalvoCmd generateRandomSalvo() {
        Board selfBoard = boards.get(self);

        int totalSpaceships = selfBoard.getTotalSpaceships();

        Board opponentBoard = boards.get(opponent);

        return opponentBoard.generateRandomSalvo(totalSpaceships);
    }
}

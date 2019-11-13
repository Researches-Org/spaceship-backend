package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;
import com.xl.spaceship.util.RandomUtil;

import java.util.Map;
import java.util.Objects;

public final class Game {

    private final GameId id;

    private final PlayerId self;

    private final PlayerId opponent;

    private final Map<PlayerId, Board> boards;

    private PlayerId playerTurn;

    private PlayerId winner;

    public Game(GameId id, PlayerId self, PlayerId opponent) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(self);
        Objects.requireNonNull(opponent);

        this.id = id;
        this.self = self;
        this.opponent = opponent;
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
}

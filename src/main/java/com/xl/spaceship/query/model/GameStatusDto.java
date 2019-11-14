package com.xl.spaceship.query.model;

import com.xl.spaceship.domain.model.Board;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.PlayerId;

public final class GameStatusDto {

    private final PlayerBoardDto self;

    private final PlayerBoardDto opponent;

    private final GamePlayerTurnDto game;

    public GameStatusDto(PlayerBoardDto self, PlayerBoardDto opponent, GamePlayerTurnDto game) {
        this.self = self;
        this.opponent = opponent;
        this.game = game;
    }

    public PlayerBoardDto getSelf() {
        return self;
    }

    public PlayerBoardDto getOpponent() {
        return opponent;
    }

    public GamePlayerTurnDto getGame() {
        return game;
    }

    public static GameStatusDto from(Game game) {
        PlayerId selfId = game.getSelf();
        Board selfBoard = game.getBoard(selfId);

        PlayerId opponentId = game.getOpponent();
        Board opponentBoard = game.getBoard(opponentId);

        PlayerBoardDto self = new PlayerBoardDto(selfId.getValue().toString(), selfBoard.toStringArray());
        PlayerBoardDto opponent = new PlayerBoardDto(opponentId.getValue().toString(), opponentBoard.toStringArray());

        GamePlayerTurnDto gamePlayerTurnDto = new GamePlayerTurnDto(game.getPlayerTurn().getValue().toString());

        return new GameStatusDto(self, opponent, gamePlayerTurnDto);
    }
}

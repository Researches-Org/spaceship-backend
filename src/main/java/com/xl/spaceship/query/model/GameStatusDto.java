package com.xl.spaceship.query.model;

import com.xl.spaceship.domain.model.Board;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.PlayerId;

public abstract class GameStatusDto {

    private final PlayerBoardDto self;

    private final PlayerBoardDto opponent;

    public GameStatusDto(PlayerBoardDto self, PlayerBoardDto opponent) {
        this.self = self;
        this.opponent = opponent;
    }

    public PlayerBoardDto getSelf() {
        return self;
    }

    public PlayerBoardDto getOpponent() {
        return opponent;
    }

    public static GameStatusDto from(Game game) {
        PlayerId selfId = game.getSelf();
        Board selfBoard = game.getBoard(selfId);

        PlayerId opponentId = game.getOpponent();
        Board opponentBoard = game.getBoard(opponentId);

        PlayerBoardDto self = new PlayerBoardDto(selfId.getValue().toString(), selfBoard.toStringArray());
        PlayerBoardDto opponent = new PlayerBoardDto(opponentId.getValue().toString(), opponentBoard.toStringArray());

        if (game.hasFinished()) {
            return new GameStatusWithWinnerDto(self, opponent, new GameWinnerDto(game.getWinner().getValue().toString()));
        } else {
            return new GameStatusWithPlayerTurnDto(self, opponent, new GamePlayerTurnDto(game.getPlayerTurn().getValue().toString()));
        }
    }
}

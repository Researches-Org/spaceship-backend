package com.xl.spaceship.infrasctructure.repository;

import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.domain.model.GameRepository;
import com.xl.spaceship.query.model.GameDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
final class GameInMemoryRepository implements GameRepository {

    private final Map<GameId, Game> games = new HashMap<>();

    @Override
    public void add(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public Game getById(GameId gameId) {
        Game game = games.get(gameId);

        if (game == null) {
            throw new RuntimeException("Game not found " + gameId);
        }

        return game;
    }

    @Override
    public void update(Game game) {
       games.put(game.getId(), game);
    }

    @Override
    public List<GameDto> getGames() {
        return games.values().stream()
                .map(game -> new GameDto(game.getId().getValue().toString(),
                        game.getSelf().getValue().toString(),
                        game.getOpponent().getValue().toString(),
                        game.isAutopilot(),
                        game.hasFinished(),
                        game.getPlayerTurn().getValue().toString(),
                        game.getWinner() != null ? game.getWinner().getValue().toString() : "",
                        game.getSelfBoard().getTotalSpaceships()))
                .collect(Collectors.toList());
    }


}

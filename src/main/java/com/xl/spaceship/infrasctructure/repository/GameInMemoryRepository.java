package com.xl.spaceship.infrasctructure.repository;

import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.domain.model.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
final class GameInMemoryRepository implements GameRepository {

    private final Map<GameId, Game> games = new HashMap<>();

    @Override
    public void add(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public Game getById(GameId gameId) {
        return games.get(gameId);
    }

    @Override
    public void update(Game game) {
       games.put(game.getId(), game);
    }


}

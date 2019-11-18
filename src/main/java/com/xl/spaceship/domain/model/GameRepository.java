package com.xl.spaceship.domain.model;

import com.xl.spaceship.query.model.GameDto;

import java.util.List;

public interface GameRepository {

    void add(Game game);

    Game getById(GameId gameId);

    void update(Game game);

    List<GameDto> getGames();
}

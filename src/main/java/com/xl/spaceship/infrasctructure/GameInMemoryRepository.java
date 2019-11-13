package com.xl.spaceship.infrasctructure;

import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameRepository;
import org.springframework.stereotype.Repository;

@Repository
final class GameInMemoryRepository implements GameRepository {

    @Override
    public void add(Game game) {

    }

}

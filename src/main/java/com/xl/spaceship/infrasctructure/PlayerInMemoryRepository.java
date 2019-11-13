package com.xl.spaceship.infrasctructure;

import com.xl.spaceship.domain.model.Player;
import com.xl.spaceship.domain.model.PlayerId;
import com.xl.spaceship.domain.model.PlayerName;
import com.xl.spaceship.domain.model.PlayerRepository;
import org.springframework.stereotype.Repository;

@Repository
final class PlayerInMemoryRepository implements PlayerRepository {

    private Player current;

    @Override
    public Player getCurrent() {
        if (current == null) {
            current = new Player(PlayerId.random(), PlayerName.of("Manoel Menezes"));
        }

        return current;
    }
}

package com.xl.spaceship.infrasctructure.repository;

import com.xl.spaceship.domain.model.Player;
import com.xl.spaceship.domain.model.PlayerId;
import com.xl.spaceship.domain.model.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
final class PlayerInMemoryRepository implements PlayerRepository {

    private Player self;

    private final Map<PlayerId, Player> players = new HashMap<>();

    @Override
    public Player getSelf() {
        return self;
    }

    @Override
    public void addSelf(Player player) {
        self = player;
    }

    @Override
    public void add(Player player) {
        players.put(player.getId(), player);
    }

}

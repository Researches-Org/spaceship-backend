package com.xl.spaceship.domain.model;

public interface PlayerRepository {

    Player getSelf();

    void addSelf(Player player);

    void add(Player player);

}

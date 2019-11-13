package com.xl.spaceship.domain.model;

import java.util.Objects;
import java.util.UUID;

abstract class Id {

    protected final UUID value;

    Id(UUID value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id gameId = (Id) o;
        return value.equals(gameId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

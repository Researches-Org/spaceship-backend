package com.xl.spaceship.domain.model;

import com.google.common.base.Preconditions;

import java.util.Objects;

abstract class Name {

    protected final String value;

    Name(String value, int maxLength) {
        Objects.requireNonNull(value);
        Preconditions.checkArgument(maxLength > 0, "maxLength must be positive");
        Preconditions.checkArgument(value.trim().length() <= maxLength, "value length after trim cannot exceed maxLength");

        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equals(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

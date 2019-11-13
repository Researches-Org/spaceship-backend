package com.xl.spaceship.domain.model;

public final class SpaceShips {

    private SpaceShips() {}

    public static Angle angle() {
        return new Angle();
    }

    public static AClass aClass() {
        return new AClass();
    }

    public static BClass bClass() {
        return new BClass();
    }

    public static SClass sClass() {
        return new SClass();
    }

    public static Winger winger() {
        return new Winger();
    }

    public static Spaceship[] all() {
        return new Spaceship[] {angle(), aClass(), bClass(), sClass(), winger()};
    }

}

package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public final class Shot {

    private static final String KILL = "kill";

    private static final String MISS = "miss";

    private static final String HIT = "hit";

    private final String shot;

    private final String result;

    private Shot(String shot, String result) {
        this.shot = shot;
        this.result = result;
    }

    public static Shot of(String shot, String result) {
        return new Shot(shot, result);
    }

    public static Shot hit(Position position) {
        return new Shot(position.toShot(), HIT);
    }

    public static Shot kill(Position position) {
        return new Shot(position.toShot(), KILL);
    }

    public static Shot miss(Position position) {
        return new Shot(position.toShot(), MISS);
    }

    public static Map<String, String> misses(String[] salvo) {
        Map<String, String> map = Maps.newLinkedHashMap();

        Arrays.stream(salvo).forEach(s -> map.put(s, MISS));

        return map;
    }

    public boolean isHit() {
        return result.equals(HIT);
    }

    public boolean isMiss() {
        return result.equals(MISS);
    }

    public boolean isKill() {
        return result.equals(KILL);
    }

    public String getShot() {
        return shot;
    }

    public String getResult() {
        return result;
    }
}

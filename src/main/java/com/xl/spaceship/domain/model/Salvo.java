package com.xl.spaceship.domain.model;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public final class Salvo {

    private static final String KILL = "kill";

    private static final String MISS = "miss";

    private static final String HIT = "hit";

    private final String salvo;

    private final String result;

    private Salvo(String salvo, String result) {
        this.salvo = salvo;
        this.result = result;
    }

    public static Salvo hit(Position position) {
        return new Salvo(position.toSalvo(), HIT);
    }

    public static Salvo kill(Position position) {
        return new Salvo(position.toSalvo(), KILL);
    }

    public static Salvo miss(Position position) {
        return new Salvo(position.toSalvo(), MISS);
    }

    public static Map<String, String> misses(String[] salvo) {
        Map<String, String> map = Maps.newLinkedHashMap();

        Arrays.stream(salvo).forEach(s -> map.put(s, MISS));

        return map;
    }

    public String getSalvo() {
        return salvo;
    }

    public String getResult() {
        return result;
    }
}

package com.xl.spaceship.domain.model;

public final class SpaceshipProtocol {

    private final String hostname;

    private final int port;

    private SpaceshipProtocol(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public static SpaceshipProtocol of(String hostname, int port) {
        return new SpaceshipProtocol(hostname, port);
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}

package com.xl.spaceship.application.command;

public final class SpaceshipProtocolCmd {

    private String hostname;

    private int port;

    public SpaceshipProtocolCmd() {
    }

    public SpaceshipProtocolCmd(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

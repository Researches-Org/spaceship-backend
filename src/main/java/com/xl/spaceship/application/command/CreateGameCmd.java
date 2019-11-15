package com.xl.spaceship.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CreateGameCmd {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("spaceship_protocol")
    private SpaceshipProtocolCmd spaceshipProtocol;

    public CreateGameCmd() {}

    public CreateGameCmd(String userId, String fullName, SpaceshipProtocolCmd spaceshipProtocol) {
        this.userId = userId;
        this.fullName = fullName;
        this.spaceshipProtocol = spaceshipProtocol;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public SpaceshipProtocolCmd getSpaceshipProtocol() {
        return spaceshipProtocol;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSpaceshipProtocol(SpaceshipProtocolCmd spaceshipProtocol) {
        this.spaceshipProtocol = spaceshipProtocol;
    }
}

package com.xl.spaceship.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChallengeCmd {

    @JsonProperty("spaceship_protocol")
    private final SpaceshipProtocolCmd spaceshipProtocolCmd;

    private final String rules;

    public ChallengeCmd(SpaceshipProtocolCmd spaceshipProtocolCmd, String rules) {
        this.spaceshipProtocolCmd = spaceshipProtocolCmd;
        this.rules = rules;
    }

    public SpaceshipProtocolCmd getSpaceshipProtocolCmd() {
        return spaceshipProtocolCmd;
    }

    public String getRules() {
        return rules;
    }
}

package com.xl.spaceship.domain.model;

import com.xl.spaceship.application.command.ChallengeCmd;
import com.xl.spaceship.application.command.CreateGameCmd;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.query.model.GameCreatedDto;
import com.xl.spaceship.query.model.SalvoResponseDto;

public interface GameHttpService {

    SalvoResponseDto sendSalvo(Game game, SalvoCmd cmd);

    GameCreatedDto challenge(CreateGameCmd createGameCmd, ChallengeCmd challengeCmd);
}

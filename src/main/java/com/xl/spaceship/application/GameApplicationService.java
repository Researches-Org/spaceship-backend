package com.xl.spaceship.application;

import com.xl.spaceship.application.command.ChallengeCmd;
import com.xl.spaceship.application.command.CreateGameCmd;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.application.command.SpaceshipProtocolCmd;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameHttpService;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.domain.model.GameRepository;
import com.xl.spaceship.domain.model.Player;
import com.xl.spaceship.domain.model.PlayerId;
import com.xl.spaceship.domain.model.PlayerName;
import com.xl.spaceship.domain.model.PlayerRepository;
import com.xl.spaceship.domain.model.SalvoReceivedEvent;
import com.xl.spaceship.domain.model.SpaceshipProtocol;
import com.xl.spaceship.infrasctructure.event.EventBus;
import com.xl.spaceship.query.model.GameCreatedDto;
import com.xl.spaceship.query.model.GameStatusDto;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.springframework.stereotype.Service;

@Service
public final class GameApplicationService {

    private final GameRepository gameRepository;

    private final GameHttpService gameHttpService;

    private final PlayerRepository playerRepository;

    private final EventBus eventBus;

    public GameApplicationService(GameRepository gameRepository,
                                  PlayerRepository playerRepository,
                                  GameHttpService gameHttpService,
                                  EventBus eventBus) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameHttpService = gameHttpService;
        this.eventBus = eventBus;
    }


    public GameCreatedDto create(CreateGameCmd cmd) {

        Player self = playerRepository.getSelf();

        GameId gameId = GameId.random();
        PlayerId opponent = PlayerId.of(cmd.getUserId());
        SpaceshipProtocol spaceshipProtocol = SpaceshipProtocol.of(
                cmd.getSpaceshipProtocol().getHostname(),
                cmd.getSpaceshipProtocol().getPort());
        Game game = new Game(gameId, self.getId(), self.getSpaceshipProtocol(), opponent, spaceshipProtocol);

        gameRepository.add(game);

        return new GameCreatedDto(
                self.getId().getValue().toString(),
                self.getName().getValue(),
                gameId.getValue().toString(),
                game.getPlayerTurn().getValue().toString());

    }

    public GameStatusDto getGame(GameId gameId) {
        Game game = gameRepository.getById(gameId);

        return GameStatusDto.from(game);
    }

    public SalvoResponseDto receiveSalvo(GameId gameId, SalvoCmd cmd) {
        Game game = gameRepository.getById(gameId);

        SalvoResponseDto salvoResponseDto = game.receiveSalvo(cmd);

        gameRepository.update(game);

        if (game.isAutopilot()) {
            eventBus.post(SalvoReceivedEvent.of(gameId));
        }

        return salvoResponseDto;
    }

    public SalvoResponseDto sendSalvo(GameId gameId, SalvoCmd cmd) {
        Game game = gameRepository.getById(gameId);

        SalvoResponseDto salvoResponseDto = gameHttpService.sendSalvo(game, cmd);

        game.updateOpponentBoard(salvoResponseDto);

        gameRepository.update(game);

        return salvoResponseDto;
    }

    public GameCreatedDto challenge(ChallengeCmd cmd) {
        Player self = playerRepository.getSelf();

        CreateGameCmd createGameCmd = new CreateGameCmd(
                self.getId().getValue().toString(),
                self.getName().getValue(),
                new SpaceshipProtocolCmd(self.getSpaceshipProtocol().getHostname(), self.getSpaceshipProtocol().getPort()));

        GameCreatedDto gameCreatedDto = null;

        try {
            gameCreatedDto = gameHttpService.challenge(createGameCmd, cmd);
        } catch (Exception e) {
            // TODO: log
            e.printStackTrace();
        }

        if (gameCreatedDto != null) {
            GameId gameId = GameId.of(gameCreatedDto.getGameId());

            PlayerId opponentId = PlayerId.of(gameCreatedDto.getUserId());
            PlayerName opponentName = PlayerName.of(gameCreatedDto.getFullName());
            SpaceshipProtocol opponentSpaceshipProtocol = SpaceshipProtocol.of(
                    cmd.getSpaceshipProtocolCmd().getHostname(),
                    cmd.getSpaceshipProtocolCmd().getPort());

            Player opponent = new Player(opponentId, opponentName, opponentSpaceshipProtocol);

            playerRepository.add(opponent);

            Game game = new Game(gameId, self.getId(), self.getSpaceshipProtocol(), opponentId, opponentSpaceshipProtocol);

            gameRepository.add(game);
        }

        return gameCreatedDto;
    }

    public void autopilot(GameId gameId) {
        Game game = gameRepository.getById(gameId);

        game.enableAutopilot();
    }

}

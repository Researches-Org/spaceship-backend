package com.xl.spaceship.application;

import com.xl.spaceship.application.command.CreateGameCmd;
import com.xl.spaceship.application.command.ReceiveSalvoCmd;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.domain.model.GameRepository;
import com.xl.spaceship.domain.model.Player;
import com.xl.spaceship.domain.model.PlayerId;
import com.xl.spaceship.domain.model.PlayerRepository;
import com.xl.spaceship.query.model.GameStatusDto;
import com.xl.spaceship.query.model.GameCreatedDto;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.springframework.stereotype.Service;

@Service
public final class GameApplicationService {

    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    public GameApplicationService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }


    public GameCreatedDto create(CreateGameCmd cmd) {

        Player self = playerRepository.getCurrent();

        GameId gameId = GameId.random();
        PlayerId opponent = PlayerId.of(cmd.getUserId());
        Game game = new Game(gameId, self.getId(), opponent);

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

    public SalvoResponseDto receiveSalvo(GameId gameId, ReceiveSalvoCmd cmd) {
        Game game = gameRepository.getById(gameId);

        SalvoResponseDto salvoResponseDto = game.receiveSalvo(cmd);

        gameRepository.update(game);

        return salvoResponseDto;
    }
}

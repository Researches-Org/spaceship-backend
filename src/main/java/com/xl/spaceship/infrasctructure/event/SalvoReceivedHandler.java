package com.xl.spaceship.infrasctructure.event;

import com.google.common.eventbus.Subscribe;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameHttpService;
import com.xl.spaceship.domain.model.GameRepository;
import com.xl.spaceship.domain.model.SalvoReceivedEvent;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.springframework.stereotype.Component;

@Component
public final class SalvoReceivedHandler implements DomainEventHandler<SalvoReceivedEvent> {

    private final GameRepository gameRepository;

    private final GameHttpService gameHttpService;

    public SalvoReceivedHandler(GameRepository gameRepository, GameHttpService gameHttpService) {
        this.gameRepository = gameRepository;
        this.gameHttpService = gameHttpService;
    }

    @Subscribe
    @Override
    public void handle(SalvoReceivedEvent salvoReceivedEvent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game game = gameRepository.getById(salvoReceivedEvent.getGameId());

        SalvoCmd cmd = game.generateRandomSalvo();

        SalvoResponseDto salvoResponseDto = gameHttpService.sendSalvo(game, cmd);

        game.updateOpponentBoard(salvoResponseDto);

        gameRepository.update(game);
    }
}

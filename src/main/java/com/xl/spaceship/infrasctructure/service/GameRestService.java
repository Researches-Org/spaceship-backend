package com.xl.spaceship.infrasctructure.service;

import com.xl.spaceship.application.command.ChallengeCmd;
import com.xl.spaceship.application.command.CreateGameCmd;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.application.command.SpaceshipProtocolCmd;
import com.xl.spaceship.domain.model.Game;
import com.xl.spaceship.domain.model.GameHttpService;
import com.xl.spaceship.domain.model.SpaceshipProtocol;
import com.xl.spaceship.query.model.GameCreatedDto;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
final class GameRestService implements GameHttpService {

    private final RestTemplate restTemplate;

    public GameRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SalvoResponseDto sendSalvo(Game game, SalvoCmd cmd) {
        SpaceshipProtocol spaceshipProtocol = game.getOpponentSpaceshipProtocol();
        String hostname = spaceshipProtocol.getHostname();
        int port = spaceshipProtocol.getPort();

        String url = String.format("http://%s:%d/xl-spaceship/protocol/game/%s", hostname, port, game.getId().getValue().toString());

        HttpEntity<SalvoCmd> requestEntity = new HttpEntity<>(cmd);

        ResponseEntity<SalvoResponseDto> response = restTemplate.exchange(url,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<SalvoResponseDto>() {});

        return response.getBody();
    }

    @Override
    public GameCreatedDto challenge(CreateGameCmd createGameCmd, ChallengeCmd challengeCmd) {
        SpaceshipProtocolCmd spaceshipProtocol = challengeCmd.getSpaceshipProtocolCmd();
        String hostname = spaceshipProtocol.getHostname();
        int port = spaceshipProtocol.getPort();

        String url = String.format("http://%s:%d/xl-spaceship/protocol/game/new", hostname, port);

        ResponseEntity<GameCreatedDto> responseEntity = restTemplate.postForEntity(url, createGameCmd, GameCreatedDto.class);

        return responseEntity.getBody();
    }
}

package com.xl.spaceship.presentation;

import com.xl.spaceship.application.GameApplicationService;
import com.xl.spaceship.application.command.ChallengeCmd;
import com.xl.spaceship.application.command.SalvoCmd;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.query.model.GameCreatedDto;
import com.xl.spaceship.query.model.GameDto;
import com.xl.spaceship.query.model.GameStatusDto;
import com.xl.spaceship.query.model.SalvoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/xl-spaceship/user")
public final class UserController {

    private final GameApplicationService gameApplicationService;

    public UserController(GameApplicationService gameApplicationService) {
        this.gameApplicationService = gameApplicationService;
    }

    @GetMapping("/game")
    public ResponseEntity<List<GameDto>> getGames() {
        List<GameDto> games = gameApplicationService.getGames();

        return ResponseEntity.ok(games);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameStatusDto> getGame(@PathVariable String gameId) {
        GameStatusDto gameDto = gameApplicationService.getGame(GameId.of(gameId));

        return ResponseEntity.ok(gameDto);
    }

    @PutMapping("/game/{gameId}/fire")
    public ResponseEntity<SalvoResponseDto> sendSalvo(@PathVariable String gameId, @RequestBody SalvoCmd cmd) {
        SalvoResponseDto salvoResponseDto = gameApplicationService.sendSalvo(GameId.of(gameId), cmd);

        return ResponseEntity.ok(salvoResponseDto);
    }

    @PostMapping("/game/new")
    public ResponseEntity<String> challenge(@RequestBody ChallengeCmd cmd) {
        GameCreatedDto gameCreatedDto = gameApplicationService.challenge(cmd);

        ResponseEntity<String> responseEntity;
        if (gameCreatedDto != null) {
            String uri = String.format("/xl-spaceship/user/game/%s", gameCreatedDto.getGameId());

            responseEntity = ResponseEntity.status(HttpStatus.SEE_OTHER)
                    .contentType(MediaType.TEXT_HTML)
                    .location(URI.create(uri))
                    .body("A new game has been created at " + uri);
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        return responseEntity;

    }

    @PostMapping("/game/{gameId}/auto")
    public ResponseEntity<String> autopilot(@PathVariable String gameId) {
        gameApplicationService.autopilot(GameId.of(gameId));

        return ResponseEntity.ok().build();
    }


}

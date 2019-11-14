package com.xl.spaceship.presentation;

import com.xl.spaceship.application.GameApplicationService;
import com.xl.spaceship.domain.model.GameId;
import com.xl.spaceship.query.model.GameStatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xl-spaceship/user")
public final class UserController {

    private final GameApplicationService gameApplicationService;

    public UserController(GameApplicationService gameApplicationService) {
        this.gameApplicationService = gameApplicationService;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameStatusDto> getGame(@PathVariable String gameId) {
        GameStatusDto gameDto = gameApplicationService.getGame(GameId.of(gameId));

        return ResponseEntity.ok(gameDto);
    }


}

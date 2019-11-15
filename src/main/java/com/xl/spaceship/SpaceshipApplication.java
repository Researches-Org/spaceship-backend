package com.xl.spaceship;

import com.xl.spaceship.domain.model.Player;
import com.xl.spaceship.domain.model.PlayerId;
import com.xl.spaceship.domain.model.PlayerName;
import com.xl.spaceship.domain.model.PlayerRepository;
import com.xl.spaceship.domain.model.SpaceshipProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpaceshipApplication {

	@Value("${player.name:Default User}")
	private String userName;

	@Value("${server.port:8080}")
	private int serverPort;

	@Value("${hostname:localhost}")
	private String hostname;

	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpaceshipApplication.class, args);
	}

	/**
	 * {@link RestTemplate} used to call other spaceship instance.
	 *
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Player createSelfPlayer() {
		Player self = new Player(PlayerId.random(), PlayerName.of(userName), SpaceshipProtocol.of(hostname, serverPort));

		playerRepository.addSelf(self);

		return self;
	}




}

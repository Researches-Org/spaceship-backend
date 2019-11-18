package com.xl.spaceship.query.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;

public class SalvoResponseDtoDeserializer extends StdDeserializer<SalvoResponseDto> {

    public SalvoResponseDtoDeserializer() {
        this(null);
    }

    public SalvoResponseDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SalvoResponseDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Map<String, String> salvo = Maps.newLinkedHashMap();
        node.get("salvo").fields().forEachRemaining(entry -> {
            String shot = entry.getKey();
            String result = entry.getValue().asText();
            salvo.put(shot, result);
        });

        boolean gameHadFinished = node.get("gameHadFinished").asBoolean();

        JsonNode gameNode = node.get("game");

        if (gameNode.has("won")) {
            String won = gameNode.get("won").asText();

            return new SalvoResponseWithWinnerDto(new GameWinnerDto(won), salvo, gameHadFinished);
        } else {
            String playerTurn = gameNode.get("player_turn").asText();

            return new SalvoResponseWithPlayerTurnDto(new GamePlayerTurnDto(playerTurn), salvo);
        }

    }
}

package com.xl.spaceship.infrasctructure.event;

import com.xl.spaceship.domain.model.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
public final class GuavaEventBus implements EventBus {

    private final com.google.common.eventbus.EventBus eventBus;

    public GuavaEventBus(SalvoReceivedHandler salvoReceivedHandler) {

        this.eventBus = new com.google.common.eventbus.AsyncEventBus(Executors.newSingleThreadExecutor());

        this.eventBus.register(salvoReceivedHandler);
    }

    @Override
    public void post(DomainEvent domainEvent) {
        this.eventBus.post(domainEvent);
    }
}

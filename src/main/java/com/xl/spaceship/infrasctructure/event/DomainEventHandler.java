package com.xl.spaceship.infrasctructure.event;

import com.xl.spaceship.domain.model.DomainEvent;

public interface DomainEventHandler<T extends DomainEvent> {

    void handle(T domainEvent);
}

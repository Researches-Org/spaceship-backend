package com.xl.spaceship.infrasctructure.event;

import com.xl.spaceship.domain.model.DomainEvent;

public interface EventBus {

    void post(DomainEvent domainEvent);
}

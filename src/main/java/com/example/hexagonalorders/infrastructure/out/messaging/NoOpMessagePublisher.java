package com.example.hexagonalorders.infrastructure.out.messaging;

import com.example.hexagonalorders.domain.port.out.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Implementación que no hace nada, sólo imprime por consola.
 */
@Component
public class NoOpMessagePublisher implements MessagePublisher {

    private static final Logger log = LoggerFactory.getLogger(NoOpMessagePublisher.class);

    @Override
    public void publish(String topic, String payload) {
        log.info("Would publish message to topic '{}': {}", topic, payload);
    }
}

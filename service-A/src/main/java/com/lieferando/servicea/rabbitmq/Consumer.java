package com.lieferando.servicea.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final PublisherImpl publisher;

    @RabbitListener(queues = "${messaging.current.queue}")
    public void receiveMessage(String message) {
        com.lieferando.core.functionality.Consumer.receiveMessage(message, publisher);
    }
}

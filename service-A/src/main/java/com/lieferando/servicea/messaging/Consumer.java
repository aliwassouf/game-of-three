package com.lieferando.servicea.messaging;

import com.lieferando.core.functionality.CoreConsumer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final PublisherImpl publisher;

    @RabbitListener(queues = "${messaging.current.queue}")
    public void receiveMessage(String message) {
        CoreConsumer.receiveMessage(message, publisher);
    }
}

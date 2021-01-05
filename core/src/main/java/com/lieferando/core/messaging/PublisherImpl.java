package com.lieferando.core.messaging;
import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;

@Slf4j
@AllArgsConstructor
class PublisherImpl implements Publisher {

    private final AmqpTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    public void send(GameEvent gameEvent) {
        rabbitTemplate.convertAndSend(messagingProperties.getTargetConnection().getExchange(),
                messagingProperties.getTargetConnection().getRoutingKey(), gameEvent);
        log.info("Sending the value " + gameEvent.getValue());

    }
}

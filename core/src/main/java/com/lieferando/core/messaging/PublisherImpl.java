package com.lieferando.core.messaging;

import com.lieferando.core.GameMessage;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;

@Slf4j
@AllArgsConstructor
class PublisherImpl implements Publisher {

    private final AmqpTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    public void send(GameMessage gameMessage) {
        gameMessage.setSenderId(messagingProperties.getId());
        rabbitTemplate.convertAndSend(messagingProperties.getTargetConnection().getExchange(),
                messagingProperties.getTargetConnection().getRoutingKey(), gameMessage);
        log.info("Sending the value " + gameMessage.getValue());

    }
}

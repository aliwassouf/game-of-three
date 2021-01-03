package com.lieferando.core.functionality;

import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;

@AllArgsConstructor
public class  Publisher {

    private final AmqpTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    public void send(int value) {
        rabbitTemplate.convertAndSend(messagingProperties.getTarget().getExchange(),
                messagingProperties.getTarget().getRoutingKey(), value/3);
        System.out.println("Sending the value " + value);

    }
}

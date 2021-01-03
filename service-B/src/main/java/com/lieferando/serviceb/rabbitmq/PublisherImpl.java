package com.lieferando.serviceb.rabbitmq;

import com.lieferando.core.Publisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherImpl implements Publisher {

    private final AmqpTemplate rabbitTemplate;

    public PublisherImpl(final AmqpTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${messaging.target.exchange}")
    private String exchange;
    @Value("${messaging.target.routingKey}")
    private String routingKey;

    public void send(int company) {
        rabbitTemplate.convertAndSend(exchange, routingKey, company);
        System.out.println("Send msg = " + company);

    }
}

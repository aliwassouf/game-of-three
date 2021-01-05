package com.lieferando.core.messaging;

import com.lieferando.core.GameMode;
import com.lieferando.core.NearestNumberService;
import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Slf4j
@AllArgsConstructor
class Consumer {
    private final MessagingProperties properties;
    private final Publisher publisher;

    @RabbitListener(queues = "${messaging.currentConnection.queue}")
    public void receiveMessage(GameEvent message) {
        if (message.getValue() == 1 || message.getValue() == -1) {
            log.info("You lost");

        } else {
            var nearest = NearestNumberService.find(message.getValue());
            log.info("Received Message From "+message.getSenderId() +" "+ message.getValue() + " nearest is " + nearest);
            if (GameMode.INSTANCE.isAutoReply()) {
                var gameEvent = new GameEvent(properties.getId(), nearest);
                publisher.send(gameEvent);
            }


        }
    }
}

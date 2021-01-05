package com.lieferando.core.messaging;

import com.lieferando.core.GameMode;
import com.lieferando.core.NearestNumberService;
import com.lieferando.core.gamesessionmanagment.GameSessionService;
import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.GameMessage;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Slf4j
@AllArgsConstructor
class Consumer {
    private final GameSessionService gameSessionService;

    @RabbitListener(queues = "${messaging.currentConnection.queue}")
    public void receiveMessage(GameMessage message) {
        var nearest = NearestNumberService.INSTANCE.applyAsInt(message.getValue(), 0);
        log.info("Received Message From " + message.getSenderId() + " " + message.getValue() + " on game session with id " + message.getGameSessionID()+ " nearest is " + nearest);
        if (GameMode.INSTANCE.isAutoReply()) {
            gameSessionService.send(nearest, message.getGameSessionID());
        }
    }
}

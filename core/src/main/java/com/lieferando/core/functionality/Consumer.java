package com.lieferando.core.functionality;

import com.lieferando.core.GameStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Random;


@Slf4j
@AllArgsConstructor
public class Consumer {
    private final Publisher publisher;

    @RabbitListener(queues = "${messaging.current.queue}")
    public void receiveMessage(String message) {
        int number = new Random().nextInt();
        try {
            number = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            System.out.println("Picking a random number");
        }
        log.info("Received "+ number);
        if (number == 1 || number == 0) {
            System.out.println("You lost");

        } else {
            var nearest = FinderService.findNearestNumberToThreeOf(number);
            if (GameStatus.INSTANCE.isAutoReply()) {

                publisher.send(nearest);
            }
            System.out.println("Received Message From RabbitMQ: " + number + " nearest is " + nearest);


        }
    }
}

package com.lieferando.core.functionality;

import com.lieferando.core.GameStatus;

import java.util.Random;

public abstract class CoreConsumer {
    public static void receiveMessage(String message, Publisher publisher){
        int number = new Random().nextInt();
        try {
            number = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            System.out.println("Picking a random number");
        }

        if (number == 1) {
            System.out.println("You lost");
            GameStatus.INSTANCE.setValue(0);
            GameStatus.INSTANCE.setMyTurn(true);
        } else {
            if (GameStatus.INSTANCE.isAutoReply()) {
                CoreController.addRandom(publisher);
                GameStatus.INSTANCE.setMyTurn(false);
            } else {
                System.out.println("Received Message From RabbitMQ: " + message);
                GameStatus.INSTANCE.setMyTurn(true);
            }
            GameStatus.INSTANCE.setValue(number);
        }
    }
}

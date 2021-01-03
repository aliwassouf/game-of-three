package com.lieferando.core.functionality;

import java.util.Random;

import static com.lieferando.core.GameStatus.INSTANCE;

public abstract class CoreController {

    private static int[] intArray = {-1, 0, 1};

    public static String addOne(Publisher publisher) {
        return doAdd(publisher, 1);
    }

    public static String subOne(Publisher publisher) {
        return doAdd(publisher, -1);
    }

    public static String addZero(Publisher publisher) {
        return doAdd(publisher, 0);
    }

    public static String addRandom(Publisher publisher){
        var picked  = (new Random().nextInt(intArray.length) + INSTANCE.getValue()) / 3;
        return doAdd(publisher, picked);
    }

    public static String send(int number, Publisher publisher) {
        if (!INSTANCE.isStarted()) {
            INSTANCE.setValue(number);
            publisher.send(number);
            INSTANCE.setMyTurn(false);
            return "you started a game and sent " + number;
        } else {
            return "There's a running game, wait until it ends";
        }
    }
    private static String endGame(){
        INSTANCE.setValue(0);
        INSTANCE.setMyTurn(true);
        return "You won!";
    }
    private static String doAdd(Publisher publisher, int addedValue){
        var validation = Validator.validate(INSTANCE);
        if (!validation.equals(""))
            return validation;
        var newValue = (INSTANCE.getValue() + addedValue) / 3;
        INSTANCE.setMyTurn(false);
        publisher.send(newValue);
        if (newValue <= 1) {
            return endGame();
        }
        return "Sent " + newValue;
    }
}

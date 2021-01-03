package com.lieferando.core.functionality;

import com.lieferando.core.GameStatus;

public abstract class Validator {

    public static String validate(GameStatus instance){
        if (!GameStatus.INSTANCE.isMyTurn()) {
            return "It's not you're turn";
        }
        if (GameStatus.INSTANCE.isAutoReply()) {
            return "You need to change to manual reply by sending POST to /manual";
        }
        if (!GameStatus.INSTANCE.isStarted()) {
            return "There's no game";
        }
        return "";
    }
}

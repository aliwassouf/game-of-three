package com.lieferando.core;

import com.lieferando.core.gamesessionmanagment.db.GameEvent;

public class GameEventMessageMapper {


    public static GameMessage toGameMessage(GameEvent gameEvent) {
        return new GameMessage(gameEvent.getId(), gameEvent.getValue(), gameEvent.getGameSession().getId(), gameEvent.getSenderId());
    }
}

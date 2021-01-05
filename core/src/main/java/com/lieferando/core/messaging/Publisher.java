package com.lieferando.core.messaging;

import com.lieferando.core.gamesessionmanagment.db.GameEvent;

public interface Publisher {
    void send(GameEvent gameEvent);
}

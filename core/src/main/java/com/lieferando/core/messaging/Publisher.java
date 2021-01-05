package com.lieferando.core.messaging;

import com.lieferando.core.GameMessage;

public interface Publisher {
    void send(GameMessage gameMessage);
}

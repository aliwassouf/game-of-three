package com.lieferando.core.gamesessionmanagment;

import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.gamesessionmanagment.db.GameSession;

import java.util.List;
import java.util.Optional;

public interface GameSessionService {

    String send(int number, Long gameSessionId);
    List<GameSession> showHistory(Long gameSessionId);
    Optional<GameEvent> getLastGameEvent(Long gameSessionId);
}

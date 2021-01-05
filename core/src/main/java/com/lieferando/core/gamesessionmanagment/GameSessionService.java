package com.lieferando.core.gamesessionmanagment;

import com.lieferando.core.NearestNumberService;
import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.gamesessionmanagment.db.GameSession;
import com.lieferando.core.messaging.Publisher;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class GameSessionService {

    private final MessagingProperties messagingProperties;
    private final GameEventRepository gameEventRepository;
    private final GameSessionRepository gameSessionRepository;
    private final Publisher publisher;


    
    public String addOne(Long gameSessionId) {
        var lastEvent = getLastGameEvent(gameSessionRepository.getOne(gameSessionId));
        var lastEventCopy = new GameEvent(lastEvent);
        if (!isCorrectTurnForGame(lastEventCopy)) {
            return "It's not your turn";
        }
        lastEventCopy.setValue(NearestNumberService.findByAddingOne(lastEvent.getValue()));
        lastEventCopy.setSenderId(messagingProperties.getId());
        var saved = gameEventRepository.save(lastEvent);
        publisher.send(saved);
        return "Sent";

    }

    
    public String addZero(Long gameSessionId) {
        var lastEvent = getLastGameEvent(gameSessionRepository.getOne(gameSessionId));
        var lastEventCopy = new GameEvent(lastEvent);
        if (!isCorrectTurnForGame(lastEventCopy)) {
            return "It's not your turn";
        }
        lastEventCopy.setValue(NearestNumberService.find(lastEvent.getValue()));
        lastEventCopy.setSenderId(messagingProperties.getId());
        var saved = gameEventRepository.save(lastEvent);
        publisher.send(saved);
        return "Sent";
    }

    
    public String subOne(Long gameSessionId) {
        var lastEvent = getLastGameEvent(gameSessionRepository.getOne(gameSessionId));
        var lastEventCopy = new GameEvent(lastEvent);
        if (!isCorrectTurnForGame(lastEventCopy)) {
            return "It's not your turn";
        }
        lastEventCopy.setValue(NearestNumberService.findBySubtractingOne(lastEvent.getValue()));
        lastEventCopy.setSenderId(messagingProperties.getId());
        var saved = gameEventRepository.save(lastEvent);
        publisher.send(saved);
        return "Sent";
    }

    
    public String send(int number) {
        if(number == 0)
            throw new IllegalStateException("Zero is gonna cause an infinite loop");
        var gameSession = gameSessionRepository.save(new GameSession());
        var gameEvent = new GameEvent(messagingProperties.getId(), number);
        gameEvent.setGameSession(gameSession);
        gameEventRepository.save(gameEvent);
        publisher.send(gameEvent);
        return "Sent";

    }

    
    public List<GameSession> showHistory(Long gameSessionId) {
        return gameSessionRepository.findAll();
    }

    private boolean isCorrectTurnForGame(GameEvent lastEven) {
        return lastEven.getSenderId().equals(messagingProperties.getId());
    }

    private GameEvent getLastGameEvent(GameSession gameSession) {
        return gameEventRepository.findFirstByGameSessionOrderByCreatedAtDesc(gameSession);
    }

}

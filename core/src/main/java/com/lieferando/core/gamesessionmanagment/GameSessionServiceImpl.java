package com.lieferando.core.gamesessionmanagment;

import com.lieferando.core.GameEventMessageMapper;
import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.gamesessionmanagment.db.GameSession;
import com.lieferando.core.messaging.Publisher;
import com.lieferando.core.properties.MessagingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class GameSessionServiceImpl implements GameSessionService {

    private final MessagingProperties messagingProperties;
    private final GameEventRepository gameEventRepository;
    private final GameSessionRepository gameSessionRepository;
    private final Publisher publisher;


    public String send(int number, Long gameSessionId) {
        var currentSessionOptional = gameSessionRepository.findById(gameSessionId);
        GameSession currentSession;
        if (currentSessionOptional.isEmpty() || currentSessionOptional.get().isFinished()) {
            log.info("Starting a new game since session with id " + gameSessionId + " doesn't exist");
            currentSession = gameSessionRepository.save(new GameSession());
        } else {
            currentSession = currentSessionOptional.get();
        }

        if (number == 0) {
            currentSession.setFinished(true);
            gameSessionRepository.save(currentSession);
            var s = "Ignoring zero values " + gameSessionId;
            log.info(s);
            return s;
        }

        GameEvent latestEvent;
        var lastEventOptional = getLastGameEvent(gameSessionId);

        if (lastEventOptional.isPresent()) {
            if (!isCorrectTurnForGame(lastEventOptional.get())) {
                return "It's not you're turn";
            }
            latestEvent = new GameEvent(lastEventOptional.get());
            latestEvent.setValue(number);
        } else {
            latestEvent = new GameEvent(messagingProperties.getId(), number);
        }

        if (number == 1 || number == -1) {
            currentSession.setFinished(true);
            gameSessionRepository.save(currentSession);
            log.info("You won the game on session with id " + gameSessionId);
                return "You won the game with id " + currentSession.getId();
        }

        latestEvent.setGameSession(currentSession);
        latestEvent.setSenderId(messagingProperties.getId());
        gameEventRepository.save(latestEvent);
        publisher.send(GameEventMessageMapper.toGameMessage(latestEvent));
        return "Sent";

    }


    public List<GameSession> showHistory(Long gameSessionId) {
        return gameSessionRepository.findAll();
    }

    private boolean isCorrectTurnForGame(GameEvent lastEven) {
        return !lastEven.getSenderId().equals(messagingProperties.getId());
    }


    public Optional<GameEvent> getLastGameEvent(Long gameSessionId) {
        return gameEventRepository.findFirstByGameSessionOrderByCreatedAtDesc(gameSessionRepository.getOne(gameSessionId));
    }
}

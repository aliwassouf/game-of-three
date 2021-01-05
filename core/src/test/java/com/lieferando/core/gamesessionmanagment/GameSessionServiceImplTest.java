package com.lieferando.core.gamesessionmanagment;

import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.gamesessionmanagment.db.GameSession;
import com.lieferando.core.messaging.Publisher;
import com.lieferando.core.properties.MessagingProperties;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionServiceImplTest {

    private final MessagingProperties messagingProperties = Mockito.mock(MessagingProperties.class);
    private final GameSessionRepository gameSessionRepository = Mockito.mock(GameSessionRepository.class);
    private final GameEventRepository gameEventRepository = Mockito.mock(GameEventRepository.class);
    private final Publisher publisher = Mockito.mock(Publisher.class);

    private final GameSessionService gameSessionService = new GameSessionServiceImpl(messagingProperties, gameEventRepository, gameSessionRepository, publisher);

    @Test
    void whenSendingNonZeroToNonExistingSessionCreateSessionAndSend() {
        Long gameSessionId = 333L;
        Mockito.when(gameSessionRepository.save(ArgumentMatchers.any())).thenReturn(new GameSession());
        Mockito.when(gameSessionRepository.findById(gameSessionId)).thenReturn(Optional.empty());
        Mockito.when(gameEventRepository.findFirstByGameSessionOrderByCreatedAtDesc(gameSessionRepository.getOne(gameSessionId))).thenReturn(Optional.empty());


        assertEquals("Sent", gameSessionService.send(4, gameSessionId));


        Mockito.verify(publisher).send(ArgumentMatchers.any());
        Mockito.verify(gameSessionRepository).save(ArgumentMatchers.any());
        Mockito.verify(gameEventRepository).save(ArgumentMatchers.any());

    }

    @Test
    void whenSendingZeroToNonExistingSessionReturnIgnoreZeros() {
        Long gameSessionId = 333L;
        Mockito.when(gameSessionRepository.save(ArgumentMatchers.any())).thenReturn(new GameSession());
        Mockito.when(gameSessionRepository.findById(gameSessionId)).thenReturn(Optional.empty());

        assertTrue(gameSessionService.send(0, gameSessionId).contains("Ignoring zero values "));

        Mockito.verify(gameSessionRepository, Mockito.times(2)).save(ArgumentMatchers.any());
    }

    @Test
    void whenSendingOneOrMinusOneToSessionReturnWinningNotification() {
        Long gameSessionId = 333L;
        var gamesession = new GameSession();
        gamesession.setId(gameSessionId);

        Mockito.when(gameSessionRepository.findById(gameSessionId)).thenReturn(Optional.of(gamesession));

        assertEquals("You won the game with id 333", gameSessionService.send(1, gameSessionId));

        Mockito.verify(gameSessionRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void makPlayerCanNotSendIfNotHisTurn(){
        Long gameSessionId = 333L;
        var gamesession = new GameSession();
        gamesession.setId(gameSessionId);

        var gameEvent = new GameEvent("PlayerOne", 5);
        gameEvent.setGameSession(gamesession);

        Mockito.when(gameEventRepository.findFirstByGameSessionOrderByCreatedAtDesc(ArgumentMatchers.any())).thenReturn(Optional.of(gameEvent));
        Mockito.when(messagingProperties.getId()).thenReturn("PlayerOne");

        assertEquals("It's not you're turn", gameSessionService.send(29384, gameSessionId));

        Mockito.verify(publisher, Mockito.never()).send(ArgumentMatchers.any());
        Mockito.verify(gameEventRepository, Mockito.never()).save(ArgumentMatchers.any());
    }


    @Test
    void makPlayerCanSendIfNotHisTurn(){
        Long gameSessionId = 333L;
        var gamesession = new GameSession();
        gamesession.setId(gameSessionId);

        var gameEvent = new GameEvent("PlayerOne", 5);
        gameEvent.setGameSession(gamesession);

        Mockito.when(gameEventRepository.findFirstByGameSessionOrderByCreatedAtDesc(ArgumentMatchers.any())).thenReturn(Optional.of(gameEvent));
        Mockito.when(messagingProperties.getId()).thenReturn("PlayerTwo");
        Mockito.when(gameSessionRepository.save(ArgumentMatchers.any())).thenReturn(new GameSession());

        assertEquals("Sent", gameSessionService.send(29384, gameSessionId));

        Mockito.verify(publisher).send(ArgumentMatchers.any());
        Mockito.verify(gameEventRepository).save(ArgumentMatchers.any());
    }
}
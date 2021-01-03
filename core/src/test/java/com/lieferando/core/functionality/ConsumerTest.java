package com.lieferando.core.functionality;

import com.lieferando.core.GameStatus;
import com.lieferando.core.Publisher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {
    private final Publisher publisher = Mockito.mock(Publisher.class);

    @Test
    void whenConsumingOneResetGameValues() {
        Consumer.receiveMessage("1", publisher);
        assertEquals(0, GameStatus.INSTANCE.getValue());
        assertTrue(GameStatus.INSTANCE.isMyTurn());
    }

    @Test
    void makeSureYouPublishOnAutoMode(){
        GameStatus.INSTANCE.setAutoReply(true);
        Consumer.receiveMessage("888", publisher);
        Mockito.verify(publisher).send(ArgumentMatchers.anyInt());
        assertFalse(GameStatus.INSTANCE.isMyTurn());
    }

    @Test
    void makeSureYouDontPublishOnManualMode(){
        GameStatus.INSTANCE.setAutoReply(false);
        Consumer.receiveMessage("888", publisher);
        Mockito.verify(publisher, Mockito.never()).send(ArgumentMatchers.anyInt());
    }

}
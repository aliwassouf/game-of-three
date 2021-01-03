package com.lieferando.core.functionality;

import com.lieferando.core.GameStatus;
import com.lieferando.core.Publisher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class CoreControllerTest {

    private final Publisher publisher = Mockito.mock(Publisher.class);

    @Test
    void whenAddingOnYourTurnSucceed(){
        GameStatus.INSTANCE.setValue(9);
        GameStatus.INSTANCE.setMyTurn(true);
        CoreController.addOne(publisher);
        Mockito.verify(publisher).send(ArgumentMatchers.anyInt());
    }
    @Test
    void whenAddingOneNotYourTurnFail(){
        GameStatus.INSTANCE.setValue(9);
        GameStatus.INSTANCE.setMyTurn(false);
        CoreController.addOne(publisher);
        Mockito.verify(publisher, Mockito.never()).send(ArgumentMatchers.anyInt());
    }

    @Test
    void whenAddingManuallyOnAutoPlayFail(){
        GameStatus.INSTANCE.setAutoReply(true);
        CoreController.addOne(publisher);
        Mockito.verify(publisher, Mockito.never()).send(ArgumentMatchers.anyInt());
    }

    @Test
    void whenAddingOneToNonStartedGameFail(){
        GameStatus.INSTANCE.setValue(0);
        CoreController.addOne(publisher);
        Mockito.verify(publisher, Mockito.never()).send(ArgumentMatchers.anyInt());
    }
}
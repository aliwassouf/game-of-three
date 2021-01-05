package com.lieferando.servicea.controller;

import com.lieferando.core.NearestNumberService;
import com.lieferando.core.gamesessionmanagment.GameSessionService;
import com.lieferando.core.gamesessionmanagment.db.GameSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lieferando.core.GameMode.INSTANCE;

@Slf4j
@RestController
@AllArgsConstructor
public class Controller {

    private final GameSessionService gameSessionService;

    @PostMapping("/start/{number}")
    public String send(@PathVariable("number") int number) {
        return gameSessionService.send(number, 0L);
    }

    @PostMapping("/{gameSessionId}/addOne")
    public String addOne(@PathVariable("gameSessionId") long gameSessionId) {
        var latestEvent = gameSessionService.getLastGameEvent(gameSessionId);
        if (latestEvent.isPresent())

            return gameSessionService.send(NearestNumberService.INSTANCE.applyAsInt(latestEvent.get().getValue(), 1), gameSessionId);
        return "You haven't received an event on this game yet";
    }


    @PostMapping("/{gameSessionId}/subtractOne")
    public String subtractOne(@PathVariable("gameSessionId") long gameSessionId) {
        var latestEvent = gameSessionService.getLastGameEvent(gameSessionId);
        if (latestEvent.isPresent())

            return gameSessionService.send(NearestNumberService.INSTANCE.applyAsInt(latestEvent.get().getValue(), -1), gameSessionId);
        return "You haven't received an event on this game yet";
    }

    @PostMapping("/{gameSessionId}/addZero")
    public String addZero(@PathVariable("gameSessionId") long gameSessionId) {
        var latestEvent = gameSessionService.getLastGameEvent(gameSessionId);
        if (latestEvent.isPresent())

            return gameSessionService.send(NearestNumberService.INSTANCE.applyAsInt(latestEvent.get().getValue(), 0), gameSessionId);
        return "You haven't received an event on this game yet";
    }

    @GetMapping("/{gameSessionId}/gameHistory")
    public List<GameSession> getGameHistory(@PathVariable("gameSessionId") long gameSessionId) {
        return gameSessionService.showHistory(gameSessionId);
    }

    @PostMapping("/automatic")
    public String setReplyToAuto() {
        INSTANCE.setAutoReply(true);
        log.info("Setting reply to auto");
        return "done";

    }

    @PostMapping("/manual")
    public String setReplyToManual() {
        INSTANCE.setAutoReply(false);
        log.info("Setting reply to manual");
        return "done";
    }
}

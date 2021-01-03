package com.lieferando.servicea.controller;

import com.lieferando.core.functionality.FinderService;
import com.lieferando.core.functionality.Publisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import java.util.Random;

import static com.lieferando.core.GameStatus.INSTANCE;

@Slf4j
@RestController
@AllArgsConstructor
public class Controller {

    private final Publisher publisher;

    @PostMapping("/start/{number}")
    public void send(@PathVariable("number") int number) {
        log.info("Sending number " + number);
        publisher.send(FinderService.findNearestNumberToThreeOf(number));
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

    @PostConstruct
    public void postConstruct(){
        var number = (new Random()).nextInt();
        log.info("Sending number " + number);
        publisher.send(FinderService.findNearestNumberToThreeOf(number));
    }
}

package com.lieferando.serviceb.controller;

import com.lieferando.core.functionality.CoreController;
import com.lieferando.serviceb.messaging.PublisherImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lieferando.core.GameStatus.*;

@RestController
@AllArgsConstructor
public class Controller {

    private final PublisherImpl publisher;

    @PostMapping("/send/{number}")
    public String send(@PathVariable("number") int number) {
        return CoreController.send(number, publisher);
    }


    @PostMapping("/autmatic")
    public String setReplyToAuto() {
        INSTANCE.setAutoReply(true);
        return "done";

    }

    @PostMapping("/manual")
    public String setReplyToManual() {
        INSTANCE.setAutoReply(false);
        return "done";
    }

    @PostMapping("/addOne")
    public String addOne() {
        return CoreController.addOne(publisher);
    }

    @PostMapping("/subOne")
    public String subOne() {
        return CoreController.subOne(publisher);

    }

    @PostMapping("/addZero")
    public String addZero() {
        return CoreController.addZero(publisher);

    }
}

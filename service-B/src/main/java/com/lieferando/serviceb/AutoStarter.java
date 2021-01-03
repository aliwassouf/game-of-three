package com.lieferando.serviceb;

import com.lieferando.core.functionality.FinderService;
import com.lieferando.core.functionality.Publisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@AllArgsConstructor
@Component
@Slf4j
public class AutoStarter {
    private Publisher publisher;

    @PostConstruct
    public void postConstruct(){
        var number = (new Random()).nextInt();
        log.info("Sending number " + number);
        publisher.send(FinderService.findNearestNumberToThreeOf(number));
    }

}

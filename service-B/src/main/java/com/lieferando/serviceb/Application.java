package com.lieferando.serviceb;

import com.lieferando.core.SetUpConnection;
import com.lieferando.core.functionality.FinderService;
import com.lieferando.core.functionality.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SetUpConnection
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(Publisher publisher) {
        return args -> {
            var number = (new Random()).nextInt();
            log.info("Sending number " + number);
            publisher.send(FinderService.findNearestNumberToThreeOf(number));
        };
    }
}

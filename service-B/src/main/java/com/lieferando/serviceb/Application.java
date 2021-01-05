package com.lieferando.serviceb;

import com.lieferando.core.SetUpConnection;
import com.lieferando.core.gamesessionmanagment.GameSessionService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Random;

@SetUpConnection
@SpringBootApplication(scanBasePackages = "com.lieferando")
@EntityScan("com.lieferando")
@EnableJpaRepositories(basePackages = "com.lieferando")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(GameSessionService gameSessionService) {
        return args -> {
            var number = (new Random()).nextInt();
           gameSessionService.send(number);
        };
    }
}

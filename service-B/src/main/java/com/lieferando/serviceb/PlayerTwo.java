package com.lieferando.serviceb;

import com.lieferando.core.GameMode;
import com.lieferando.core.SetUpConnection;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SetUpConnection
public class PlayerTwo {

    public static void main(String[] args) {
        SpringApplication.run(PlayerTwo.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            GameMode.INSTANCE.setAutoReply(true);
        };
    }
}

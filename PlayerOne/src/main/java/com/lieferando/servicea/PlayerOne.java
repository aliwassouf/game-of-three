package com.lieferando.servicea;

import com.lieferando.core.GameMode;
import com.lieferando.core.SetUpConnection;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SetUpConnection
public class PlayerOne {

    public static void main(String[] args) {
        SpringApplication.run(PlayerOne.class, args);
    }
    @Bean
    public ApplicationRunner runner() {
        return args -> {
            GameMode.INSTANCE.setAutoReply(false);
        };
    }
}

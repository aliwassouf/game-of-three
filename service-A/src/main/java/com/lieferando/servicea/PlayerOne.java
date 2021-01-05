package com.lieferando.servicea;

import com.lieferando.core.GameMode;
import com.lieferando.core.SetUpConnection;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SetUpConnection
@SpringBootApplication(scanBasePackages = "com.lieferando")
@EntityScan("com.lieferando")
@EnableJpaRepositories(basePackages = "com.lieferando")
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

package com.lieferando.core;


import com.lieferando.core.messaging.AutoConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfig.class})
@SpringBootApplication(scanBasePackages = "com.lieferando")
@EntityScan("com.lieferando")
@EnableJpaRepositories(basePackages = "com.lieferando")
public @interface SetUpConnection {
}

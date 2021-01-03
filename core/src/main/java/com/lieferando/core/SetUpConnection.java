package com.lieferando.core;


import com.lieferando.core.config.MessagingAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MessagingAutoConfig.class})
public @interface SetUpConnection {
}

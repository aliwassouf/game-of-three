package com.lieferando.core;


import com.lieferando.core.messaging.AutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfig.class})
public @interface SetUpConnection {
}

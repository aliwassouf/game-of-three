package com.lieferando.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messaging")
@Component
@Data
public class MessagingProperties {

    private String id;
    private CurrentConnection currentConnection;
    private TargetConnection targetConnection;
}

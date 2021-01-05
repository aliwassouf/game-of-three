package com.lieferando.core.properties;

import lombok.Data;

@Data
public class CurrentConnection {
    private String exchange;
    private String routingKey;
    private String queue;
}

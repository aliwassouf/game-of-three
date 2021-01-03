package com.lieferando.core.properties;

import lombok.Data;

@Data
public class Current {
    private String exchange;
    private String routingKey;
    private String queue;
}

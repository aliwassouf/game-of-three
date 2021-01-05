package com.lieferando.core.properties;


import lombok.Data;

@Data
public class TargetConnection {
    private String exchange;
    private String routingKey;
}

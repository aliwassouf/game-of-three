package com.lieferando.core.properties;


import lombok.Data;

@Data
public class Target {
    private String exchange;
    private String routingKey;
}

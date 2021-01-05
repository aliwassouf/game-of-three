package com.lieferando.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMessage {
    private Long gameEventId;
    private int value;
    private Long gameSessionID;
    private String senderId;
}

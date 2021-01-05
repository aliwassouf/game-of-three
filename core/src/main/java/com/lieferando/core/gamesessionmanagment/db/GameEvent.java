package com.lieferando.core.gamesessionmanagment.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GAME_EVENTS")
public class GameEvent implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String senderId;
    @Getter
    @Setter
    private int value;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    @Setter
    @Getter
    private GameSession gameSession;


    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public GameEvent(String senderId, int value) {
        this.senderId = senderId;
        this.value = value;
    }

    public GameEvent() {
    }

   public GameEvent(GameEvent gameEvent){
        this.gameSession = gameEvent.gameSession;
        this.value = gameEvent.value;
        this.senderId = gameEvent.senderId;
   }
}

package com.lieferando.core.gamesessionmanagment.db;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@Where(clause = "finished = false")
@Table(name = "GAME_SESSIONS")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean finished;
}

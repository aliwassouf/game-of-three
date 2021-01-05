package com.lieferando.core.gamesessionmanagment.db;


import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Where(clause = "finished = false")
@Table(name = "GAME_SESSIONS")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

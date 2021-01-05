package com.lieferando.core.gamesessionmanagment;


import com.lieferando.core.gamesessionmanagment.db.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findAll();
}

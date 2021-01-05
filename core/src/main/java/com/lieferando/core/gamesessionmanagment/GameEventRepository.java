package com.lieferando.core.gamesessionmanagment;


import com.lieferando.core.gamesessionmanagment.db.GameEvent;
import com.lieferando.core.gamesessionmanagment.db.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {

    Optional<GameEvent> findFirstByGameSessionOrderByCreatedAtDesc(GameSession gameSession);
}

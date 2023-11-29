package com.example.springboot.repositories;

import com.example.springboot.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Long> {
    Player findPlayerById(Long id);
}

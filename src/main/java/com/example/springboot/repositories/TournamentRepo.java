package com.example.springboot.repositories;

import com.example.springboot.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament,Long> {
    Tournament findTournamentById(Long id);
}

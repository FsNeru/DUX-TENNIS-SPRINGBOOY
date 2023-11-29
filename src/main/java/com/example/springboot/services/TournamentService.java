package com.example.springboot.services;



import com.example.springboot.models.Tournament;

public interface TournamentService {

        Tournament addTournament (Long id, String tournamentName, int tournamentSets);

        Tournament findTournamentById(Long id);
}

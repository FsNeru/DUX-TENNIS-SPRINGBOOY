package com.example.springboot.controller;

import com.example.springboot.models.Tournament;
import com.example.springboot.services.TournamentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class TournamentControllerTest {
    @InjectMocks
    private TournamentController tournamentController;

    @Mock
    private TournamentServiceImpl tournamentServiceImpl;

    @Test
    public void testAddTournament() {
        Tournament tournament = new Tournament();
        tournament.setTournamentName("Torneo de Prueba");
        tournament.setTournamentSets(5);


        when(tournamentServiceImpl.addTournament(any(), any(), anyInt()))
                .thenReturn(tournament);


        ResponseEntity<Object> responseEntity = tournamentController.addTournament(1L, "Sample Tournament", 5);


        assertEquals(ResponseEntity.ok(tournament), responseEntity);
    }

    @Test
    public void testPlayTournament() {
        Tournament tournament = new Tournament();
        tournament.setId(1L);


        when(tournamentServiceImpl.findTournamentById(1L))
                .thenReturn(tournament);


        ResponseEntity<Object> responseEntity = tournamentController.playTournament();


        verify(tournamentServiceImpl, times(1)).findTournamentById(1L);


        verify(tournamentServiceImpl, times(1)).playMatch(tournament);


        assertEquals(ResponseEntity.ok(tournament), responseEntity);
    }

}


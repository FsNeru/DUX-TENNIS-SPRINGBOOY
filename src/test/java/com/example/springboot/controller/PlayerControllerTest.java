package com.example.springboot.controller;

import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;
import com.example.springboot.services.PlayerService;
import com.example.springboot.services.PlayerServiceImpl;
import com.example.springboot.services.TournamentService;
import com.example.springboot.services.TournamentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerServiceImpl playerService;

    @Mock
    private TournamentServiceImpl tournamentService;

    @Test
    public void testAddPlayer() {
        // Mock data
        Player player = new Player();
        player.setPlayerName("Player de Prueba");
        player.setChanceToWin(50);

        // Mock the behavior of playerService.addPlayer
        when(playerService.addPlayer(any(), any(), any(), anyInt()))
                .thenReturn(player);

        // Perform the test
        ResponseEntity<Object> responseEntity = playerController.addPlayer(1L, "Player de Prueba", 50);

        // Assertions
        assertEquals(ResponseEntity.ok(player), responseEntity);
    }
}

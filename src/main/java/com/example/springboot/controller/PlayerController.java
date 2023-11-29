package com.example.springboot.controller;

import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;
import com.example.springboot.services.PlayerServiceImpl;
import com.example.springboot.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {

    private final PlayerServiceImpl playerServiceImpl;
    private final TournamentServiceImpl tournamentServiceImpl;
    @Autowired
    public PlayerController(PlayerServiceImpl playerServiceImpl, TournamentServiceImpl tournamentServiceImpl) {
        this.playerServiceImpl = playerServiceImpl;
        this.tournamentServiceImpl = tournamentServiceImpl;
    }


    @PostMapping("/addPlayer")
    public ResponseEntity<Object> addPlayer(@RequestParam Long id ,@RequestParam String playerName, @RequestParam int chanceToWin){
        Tournament newTournament = tournamentServiceImpl.findTournamentById(1l);
        Player newPlayer = playerServiceImpl.addPlayer(newTournament,id,playerName,chanceToWin);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }
}

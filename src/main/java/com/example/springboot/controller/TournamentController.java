package com.example.springboot.controller;


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
public class TournamentController {
    private final TournamentServiceImpl tournamentServiceImpl;
    private final PlayerServiceImpl playerServiceImpl;
    @Autowired
    public TournamentController(TournamentServiceImpl tournamentServiceImpl, PlayerServiceImpl playerServiceImpl) {
        this.tournamentServiceImpl = tournamentServiceImpl;
        this.playerServiceImpl = playerServiceImpl;
    }

    @PostMapping("/addTournament")
    public ResponseEntity<Object> addTournament(@RequestParam Long id, @RequestParam String tournamentName, @RequestParam int tournamentSets){
        Tournament newTournament = tournamentServiceImpl.addTournament(id,tournamentName,tournamentSets);
        return new ResponseEntity<>(newTournament, HttpStatus.OK);
    }
    @PostMapping("/playTournament")
    public ResponseEntity<Object> playTournament(){
        Tournament newTournament = tournamentServiceImpl.findTournamentById(1l);
        tournamentServiceImpl.playMatch(newTournament);
        return new ResponseEntity<>(newTournament, HttpStatus.OK);
    }

}

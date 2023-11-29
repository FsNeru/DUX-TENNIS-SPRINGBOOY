package com.example.springboot.services;

import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;
import com.example.springboot.repositories.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepo playerRepo;
@Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }


    @Override
    public Player addPlayer(Tournament tournament,Long id, String playerName, int chanceToWin) {
        Player newPlayer = new Player(id,playerName,chanceToWin);
        tournament.addPlayers(newPlayer);
        newPlayer.setTournament(tournament);

        return playerRepo.saveAndFlush(newPlayer);
    }
    @Override
    public Player findPlayerById(Long id){
    return  playerRepo.findPlayerById(id);
    }
}

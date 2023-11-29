package com.example.springboot.services;


import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;

public interface PlayerService {

    Player addPlayer (Tournament tournament,Long id, String playerName, int chanceToWin);
    Player findPlayerById(Long id);
}

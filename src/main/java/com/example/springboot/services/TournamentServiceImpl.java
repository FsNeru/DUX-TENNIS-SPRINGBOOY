package com.example.springboot.services;

import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;
import com.example.springboot.repositories.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TournamentServiceImpl implements TournamentService{
    private final TournamentRepo tournamentRepo;
    @Autowired
    public TournamentServiceImpl(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }


    @Override
    public Tournament addTournament(Long id, String tournamentName, int tournamentSets) {
        Tournament newTournament = new Tournament(id,tournamentName,tournamentSets);

        return tournamentRepo.saveAndFlush(newTournament);
    }
    @Override
    public Tournament findTournamentById(Long id){
        return  tournamentRepo.findTournamentById(id);
    }

    public Tournament playMatch(Tournament tournament) {
        Random winnerPoint = new Random();
        List<Player> players = new ArrayList<>();
        players.addAll(tournament.getPlayers());
        int[][] gamesAndSetBoard = new int[2][tournament.getTournamentSets()];
        int[]  pointsBoard= new int[2];
        tournament.tournamentPresentation(players);
        while (!tournament.finishMatch()){
            while (!tournament.finishSet(players)) {
                tournament.PlayerServe(players);
                while(!tournament.FinishGame()) {
                    tournament.UpdateAndPrintPointBoard(pointsBoard, players);
                    int randomNumber = winnerPoint.nextInt(100) + 1;
                    if (randomNumber <= players.get(0).getChanceToWin()) {
                        tournament.addPoints(1);
                    } else {
                        tournament.addPoints(2);
                    }
                }
                tournament.addGames(players);
                tournament.updateAndPrintGamesAndSetBoard(gamesAndSetBoard, players);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        tournament.printWinner(players,tournament);
        tournament.updateAndPrintGamesAndSetBoard(gamesAndSetBoard, players);
        return tournament;
    }
}


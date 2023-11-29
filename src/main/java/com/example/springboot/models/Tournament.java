package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tournamentName;
    private int tournamentSets;

    @OneToMany(mappedBy="tournament", fetch=FetchType.LAZY)
    private Set<Player> players = new HashSet<>();

    private int player1Points;
    private int player2Points;

    private int player1Games;
    private int player2Games;
    private int playingSetNumber = 0;

    private int playingGameNumber = 1;

    private int setsWinByPlayer1;
    private int setsWinByPlayer2;




    public Tournament() {
    }

    public Tournament(Long id, String tournamentName, int tournamentSets) {
        this.id = id;
        this.tournamentName = tournamentName;
        this.tournamentSets = tournamentSets;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getTournamentSets() {
        return tournamentSets;
    }

    public void setTournamentSets(int tournamentSets) {
        this.tournamentSets = tournamentSets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(int player1Points) {
        this.player1Points = player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(int player2Points) {
        this.player2Points = player2Points;
    }

    public int getPlayer1Games() {
        return player1Games;
    }

    public void setPlayer1Games(int player1Games) {
        this.player1Games = player1Games;
    }

    public int getPlayer2Games() {
        return player2Games;
    }

    public void setPlayer2Games(int player2Games) {
        this.player2Games = player2Games;
    }

    public int getPlayingSetNumber() {
        return playingSetNumber;
    }

    public void setPlayingSetNumber(int playingSetNumber) {
        this.playingSetNumber = playingSetNumber;
    }

    @JsonIgnore()
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @JsonIgnore()
    public void addPlayers(Player player) {
        player.setTournament(this);
        players.add(player);
    }

    public int getSetsWinByPlayer1() {
        return setsWinByPlayer1;
    }

    public void setSetsWinByPlayer1(int setsWinByPlayer1) {
        this.setsWinByPlayer1 = setsWinByPlayer1;
    }

    public int getSetsWinByPlayer2() {
        return setsWinByPlayer2;
    }

    public void setSetsWinByPlayer2(int setsWinByPlayer2) {
        this.setsWinByPlayer2 = setsWinByPlayer2;
    }

    public int getPlayingGameNumber() {
        return playingGameNumber;
    }

    public void setPlayingGameNumber(int playingGameNumber) {
        this.playingGameNumber = playingGameNumber;
    }

    public void addPoints(int player) {
        if(player == 1) {
            if (player1Points < 30) {
                player1Points += 15;
            } else if (player1Points >= 30) {
                player1Points += 10;
            }
        }
        else {
            if (player2Points < 30) {
                player2Points += 15;
            } else if (player2Points >= 30) {
                player2Points += 10;
            }
        }
    }
    public void addGames(List<Player> players) {
        if (player1Points >= 50 & player1Points - 10 > player2Points) {
            player1Games += 1;
            System.out.println("*** "+players.get(0).getPlayerName() + " GAME ***");
            resetPoints();
        } else if (player2Points >= 50 & player2Points - 10 > player1Points) {
            player2Games += 1;
            System.out.println("***" + players.get(1).getPlayerName() + " GAME ***");
            resetPoints();
        }
    }

    public void resetPoints() {
        this.player1Points = 0;
        this.player2Points = 0;
    }
    public void resetGames() {
        this.player1Games = 0;
        this.player2Games = 0;
    }
    public void newSet(){
        this.playingSetNumber ++;
    }
    public void resetSet(){
        resetPoints();
        resetGames();
    }

    public void rematch(){
        resetPoints();
        resetGames();
        resetSet();
    }

    public boolean FinishGame(){
        if(player1Points >= 50 & player1Points - 10 > player2Points || player2Points >= 50 & player2Points - 10 > player1Points  ){
            playingGameNumber++;
            return true;
        }
        return false;
    }

    public boolean finishSet(List<Player> players){

        if(player1Games >= 6 & player1Games -1  > player2Games)
        {
            setsWinByPlayer1++;
            newSet();
            resetSet();
            System.out.println("*** " + players.get(0).getPlayerName() + " SET ***");
            return true;
        } else if (player2Games >= 6 & player2Games -1  > player1Games) {
            setsWinByPlayer2++;
            newSet();
            resetSet();
            System.out.println("*** " + players.get(1).getPlayerName() + " SET ***");
            return true;
        }
        return false;
    }
    public boolean finishMatch(){;
        if (tournamentSets == 5)
        {
            if (setsWinByPlayer1 == 3 || setsWinByPlayer2 ==3) return true;
        }
        else{
            if( setsWinByPlayer1 == 2 || setsWinByPlayer2 ==2) return  true;
        }
        return false;

    }

    public void UpdateAndPrintPointBoard(int[] pointBoard, List<Player> players){
        pointBoard[0] = player1Points;
        pointBoard[1] = player2Points;
        System.out.println("°°°°°°PointsBoard°°°°°°");
        for (int i = 0; i < pointBoard.length; i++){
            System.out.println(players.get(i).getPlayerName() + " " + pointBoard[i]);
        }
        System.out.println("-----------------------");
    }
    public void updateAndPrintGamesAndSetBoard(int[][] gamesAndSetBoard, List<Player> players)
    {
        if (playingSetNumber  < 5)
        {
            gamesAndSetBoard[0][playingSetNumber] = player1Games;
            gamesAndSetBoard[1][playingSetNumber] = player2Games;
        }
        System.out.println("======MatchBoard======");
        for (int i = 0; i < gamesAndSetBoard.length; i++) {
            System.out.print(players.get(i).getPlayerName()+ " ");
            for (int j = 0; j < gamesAndSetBoard[i].length; j++) {
                System.out.print(gamesAndSetBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }
    public void printWinner(List<Player> players, Tournament tournament){
        if (tournamentSets == 5 && setsWinByPlayer1 == 3)
        {
            System.out.println("***********************************************");
            System.out.println("WINNER OF "+ tournament.getTournamentName() + " 2023 FINALS IS:");
            System.out.println("***********************************************");
            System.out.println("*** " + players.get(0).getPlayerName() + " ***");
        }
        else {
            System.out.println("***********************************************");
            System.out.println("WINNER OF "+ tournament.getTournamentName() + " 2023 FINALS IS:");
            System.out.println("***********************************************");
            System.out.println("*** " + players.get(1).getPlayerName() + " ***");
        }
    }
    public void PlayerServe(List<Player> players){
        if(playingGameNumber % 2 == 0){
            System.out.println(players.get(0).getPlayerName()+ " Serve");
        }
        else {
            System.out.println(players.get(1).getPlayerName()+ " Serve");
        }

    }

    public void tournamentPresentation(List<Player> players) {
        System.out.println("***********************************************");
        System.out.println("Welcome to " + tournamentName + " 2023 Finals" );
        System.out.println("***********************************************");
        System.out.println("Today Match:");
        System.out.println("* " + players.get(0).getPlayerName());
        System.out.println("------ VS ------");
        System.out.println("* " +players.get(1).getPlayerName());
        System.out.println("***********************************************");
        System.out.println("--------------- Match Start  ------------------");
    }
}

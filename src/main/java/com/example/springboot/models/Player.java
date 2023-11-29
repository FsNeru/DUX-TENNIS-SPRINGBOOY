package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name= "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerName;
    private int chanceToWin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tournament_id")
    private Tournament tournament;

    public Player() {
    }

    public Player(Long id,String playerName, int chanceToWin) {
        this.id = id;
        this.playerName = playerName;
        this.chanceToWin = chanceToWin;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getChanceToWin() {
        return chanceToWin;
    }

    public void setChanceToWin(int chanceToWin) {
        this.chanceToWin = chanceToWin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore()
    public Tournament getTournament() {
        return tournament;
    }

    @JsonIgnore()
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}

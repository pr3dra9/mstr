/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Objects;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "matches")
public class Match {

    public Match() {
    }

    public Match(Long id, League league, Team homeTeam, Team awayTeam, String round, LocalDateTime date, MatchStatus status, int homeTeamGoals, int awayTeamGoals) {
        this.id = id;
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.round = round;
        this.date = date;
        this.status = status;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Match(League league, Team homeTeam, Team awayTeam, String round, LocalDateTime date, MatchStatus status, int homeTeamGoals, int awayTeamGoals) {
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.round = round;
        this.date = date;
        this.status = status;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Match(Long id, League league, Team homeTeam, Team awayTeam, String round, LocalDateTime date, MatchStatus status) {
        this.id = id;
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.round = round;
        this.date = date;
        this.status = status;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    public Match(League league, Team homeTeam, Team awayTeam, String round, LocalDateTime date, MatchStatus status) {
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.round = round;
        this.date = date;
        this.status = status;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "league_fk")
    private League league;

    @ManyToOne
    @JoinColumn(name = "home_team_fk")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_fk")
    private Team awayTeam;

    private String round;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    
    private int homeTeamGoals;
    
    private int awayTeamGoals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
    
    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", league=" + league + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", round=" + round + ", date=" + date + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.league);
        hash = 29 * hash + Objects.hashCode(this.homeTeam);
        hash = 29 * hash + Objects.hashCode(this.awayTeam);
        hash = 29 * hash + Objects.hashCode(this.round);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (!Objects.equals(this.league, other.league)) {
            return false;
        }
        if (!Objects.equals(this.homeTeam, other.homeTeam)) {
            return false;
        }
        if (!Objects.equals(this.awayTeam, other.awayTeam)) {
            return false;
        }
        return Objects.equals(this.round, other.round);
    }

}

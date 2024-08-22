/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "league")
public class League {

    public League() {
    }

    public League(String region, Integer rank, String season, String name, String logo, Integer rounds, List<Team> teams) {
        this.region = region;
        this.rank = rank;
        this.season = season;
        this.name = name;
        this.logo = logo;
        this.rounds = rounds;
        this.teams = teams;
    }
    
    public League(Long id, String region, Integer rank, String season, String name, String logo, Integer rounds, List<Team> teams) {
        this.id = id;
        this.region = region;
        this.rank = rank;
        this.season = season;
        this.name = name;
        this.logo = logo;
        this.rounds = rounds;
        this.teams = teams;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String region;
    
    @Min(value = 1)
    private Integer rank;
    
    private String season;
    
    private String name;
    
    private String logo;

    @Min(value = 1)    
    private Integer rounds;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "league_team", 
      joinColumns = @JoinColumn(name = "league_id"), 
      inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "League{" + "id=" + id + ", region=" + region + ", rank=" + rank + ", season=" + season + ", name=" + name + ", logo=" + logo + ", rounds=" + rounds + ", teams=" + teams + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.region);
        hash = 31 * hash + Objects.hashCode(this.rank);
        hash = 31 * hash + Objects.hashCode(this.season);
        hash = 31 * hash + Objects.hashCode(this.name);
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
        final League other = (League) obj;
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.season, other.season)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.rank, other.rank);
    }

}

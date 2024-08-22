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
import java.util.Objects;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchEventType;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "match_event")
public class MatchEvent {

    public MatchEvent() {
    }

    public MatchEvent(Match match, MatchEventType type, Team team, String player, Integer minute, String description) {
        this.match = match;
        this.type = type;
        this.team = team;
        this.player = player;
        this.minute = minute;
        this.description = description;
    }
    
    public MatchEvent(Long id, Match match, MatchEventType type, Team team, String player, Integer minute, String description) {
        this.id = id;
        this.match = match;
        this.type = type;
        this.team = team;
        this.player = player;
        this.minute = minute;
        this.description = description;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "match_fk")
    private Match match;
    
    @Enumerated(EnumType.STRING)
    private MatchEventType type;
    
    @ManyToOne
    @JoinColumn(name = "team_fk")
    private Team team;
    
    private String player;
    
    @Min(value = 0)
    private Integer minute;
    
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public MatchEventType getType() {
        return type;
    }

    public void setType(MatchEventType type) {
        this.type = type;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MatchEvent{" + "id=" + id + ", match=" + match + ", type=" + type.getDisplayName() + ", team=" + team + ", player=" + player + ", minute=" + minute + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.match);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.team);
        hash = 37 * hash + Objects.hashCode(this.player);
        hash = 37 * hash + Objects.hashCode(this.minute);
        hash = 37 * hash + Objects.hashCode(this.description);
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
        final MatchEvent other = (MatchEvent) obj;
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.match, other.match)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return Objects.equals(this.minute, other.minute);
    }

}

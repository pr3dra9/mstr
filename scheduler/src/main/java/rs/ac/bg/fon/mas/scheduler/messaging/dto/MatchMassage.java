/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.dto;

import java.util.Objects;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchOutcome;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchStatus;

/**
 *
 * @author Predrag
 */
public class MatchMassage {
    private Long matchId;
    private MatchStatus status;
    private MatchOutcome outcome;

    public MatchMassage() {
    }

    public MatchMassage(Long matchId, MatchOutcome outcome) {
        this.matchId = matchId;
        this.status = MatchStatus.COMPLETED;
        this.outcome = outcome;
    }
    
    public MatchMassage(Long matchId, MatchStatus status, MatchOutcome outcome) {
        this.matchId = matchId;
        this.status = status;
        this.outcome = outcome;
    }
    
    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public MatchOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(MatchOutcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.matchId);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.outcome);
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
        final MatchMassage other = (MatchMassage) obj;
        if (!Objects.equals(this.matchId, other.matchId)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return this.outcome == other.outcome;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.dto;

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
    
}

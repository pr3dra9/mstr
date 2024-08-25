/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "ticket_prediction")
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchId;
    @Enumerated(EnumType.STRING)
    private PredictionOutcome outcome;
    @Enumerated(EnumType.STRING)
    private PredictionStatus status;

    public Prediction() {
    }

    public Prediction(Long matchId, PredictionOutcome outcome, PredictionStatus status) {
        this.matchId = matchId;
        this.outcome = outcome;
        this.status = status;
    }
    
    public Prediction(Long id, Long matchId, PredictionOutcome outcome, PredictionStatus status) {
        this.id = id;
        this.matchId = matchId;
        this.outcome = outcome;
        this.status = status;
    }

    public PredictionStatus getStatus() {
        return status;
    }

    public void setStatus(PredictionStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public PredictionOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(PredictionOutcome outcome) {
        this.outcome = outcome;
    }
    
}

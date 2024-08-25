/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.service;

import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;

/**
 *
 * @author Predrag
 */
public interface PredictionService {
    
    void changeStatus(Long matchId, PredictionOutcome matchOutcome);
    
}

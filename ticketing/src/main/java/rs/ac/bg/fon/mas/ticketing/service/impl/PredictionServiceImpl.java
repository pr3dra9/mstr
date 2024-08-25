/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;
import rs.ac.bg.fon.mas.ticketing.repository.PredictionRepository;
import rs.ac.bg.fon.mas.ticketing.service.PredictionService;

/**
 *
 * @author Predrag
 */
@Service
public class PredictionServiceImpl implements PredictionService{

    @Autowired
    PredictionRepository repo;
    
    @Override
    public void changeStatus(Long matchId, PredictionOutcome matchOutcome) {
        List<Prediction> predictions = repo.findByMatchId(matchId);
        for (Prediction prediction : predictions) {
            if (prediction.getOutcome().equals(matchOutcome)) {
                prediction.setStatus(PredictionStatus.WIN);
            } else {
                prediction.setStatus(PredictionStatus.LOSE);
            }
        }
        repo.saveAll(predictions);
    }
    
}

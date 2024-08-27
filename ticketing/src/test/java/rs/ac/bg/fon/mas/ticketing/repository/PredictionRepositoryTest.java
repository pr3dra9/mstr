/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.repository;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;

/**
 *
 * @author Predrag
 */
@DataJpaTest
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PredictionRepositoryTest {

    @Autowired
    PredictionRepository repo;

    @Test
    public void testFindByMatchId() {
        Prediction p1 = new Prediction(1L, PredictionOutcome.HOME_WIN, PredictionStatus.ACTIVE);
        Prediction p2 = new Prediction(2L, PredictionOutcome.HOME_WIN, PredictionStatus.ACTIVE);
        Prediction p3 = new Prediction(1L, PredictionOutcome.DRAW, PredictionStatus.ACTIVE);
        Prediction p4 = new Prediction(1L, PredictionOutcome.AWAY_WIN, PredictionStatus.ACTIVE);

        Prediction p5 = new Prediction(3L, PredictionOutcome.HOME_WIN, PredictionStatus.LOSE);
        Prediction p6 = new Prediction(4L, PredictionOutcome.HOME_WIN, PredictionStatus.LOSE);
        Prediction p7 = new Prediction(3L, PredictionOutcome.DRAW, PredictionStatus.LOSE);
        Prediction p8 = new Prediction(3L, PredictionOutcome.AWAY_WIN, PredictionStatus.WIN);
        
        List<Prediction> savedList = null;
        try {
            savedList = repo.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
            List<Prediction> entities = repo.findByMatchId(1L);
            
            Assertions.assertEquals(3, entities.size());
        } finally {
            if (savedList != null && !savedList.isEmpty())
            repo.deleteAll(savedList);            
        }
        

        
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;

/**
 *
 * @author Predrag
 */
@Repository
public interface PredictionRepository extends  JpaRepository<Prediction, Long>{
    List<Prediction> findByMatchId(Long matchId);
}

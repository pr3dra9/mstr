/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;

/**
 *
 * @author Predrag
 */
@Repository
public interface MatchEventRepository extends JpaRepository<MatchEvent, Long>{
    public List<MatchEvent> findByMatchOrderByMinute(Match match);
}

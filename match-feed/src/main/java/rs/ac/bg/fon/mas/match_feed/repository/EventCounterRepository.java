/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;

/**
 *
 * @author Predrag
 */
public interface EventCounterRepository extends JpaRepository<EventCounter, Long>{
    Optional<EventCounter> findByUuid(Long uuid);
}

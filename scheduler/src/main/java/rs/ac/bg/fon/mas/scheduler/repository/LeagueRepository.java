/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.mas.scheduler.model.League;

/**
 *
 * @author Predrag
 */
@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{
    Optional<League> findByRegionAndSeasonAndNameAndRank(String region, String season, String name, int rank);
}

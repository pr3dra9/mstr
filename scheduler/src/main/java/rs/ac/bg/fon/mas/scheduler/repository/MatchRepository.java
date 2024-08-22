/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    
    @Query("SELECT m FROM Match m WHERE m.date >= :startDateTime AND m.date < :endDateTime")
    List<Match> findMatchesBetweenDates(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
    
    @Query("SELECT m FROM Match m WHERE m.id IN :ids")
    List<Match> findByIds(@Param("ids") List<Long> ids);
    
    Optional<Match> findByLeagueAndHomeTeamAndAwayTeamAndRound(League league, Team homeTeam, Team awayTeam, int round);
    
}

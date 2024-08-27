/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.mas.scheduler.model.League;

/**
 *
 * @author Predrag
 */
public interface LeagueService {
    League create(League league);
    Page<League> getAll(Pageable pageable);
    League get(Long id);
    League update(Long id, League league);
    void delete(Long leagueId);
    
    League addTeams(Long leagueId, Set<Long> teamIds);
    League removeTeams(Long leagueId, Set<Long> teamIds);
    League replaceTeams(Long leagueId, Set<Long> teamIds);
}

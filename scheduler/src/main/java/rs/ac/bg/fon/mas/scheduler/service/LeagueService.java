/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import java.util.List;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
public interface LeagueService {
    List<League> getAll();
    League get(Long id);
    League create(League league);
    League update(League league);
    League addTeam(Long leagueId, Team team);
    League addTeam(Long leagueId, Long teamId);
    void delete(League league);
    void delete(Long leagueId);
}

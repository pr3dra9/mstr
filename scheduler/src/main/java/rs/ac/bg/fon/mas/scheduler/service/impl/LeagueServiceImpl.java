/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.repository.LeagueRepository;
import rs.ac.bg.fon.mas.scheduler.repository.TeamRepository;
import rs.ac.bg.fon.mas.scheduler.service.LeagueService;

/**
 *
 * @author Predrag
 */
@Service
public class LeagueServiceImpl implements LeagueService {
    
    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    TeamRepository teamRepository;
    
    @Override
    public List<League> getAll() {
        return leagueRepository.findAll();
    }

    @Override
    public League get(Long id) {
        return leagueRepository.findById(id).orElse(null);
    }

    @Override
    public League create(League league) {
        League entityLeague = leagueRepository
                .findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), 
                        league.getName(), league.getRank())
                .orElse(null);
        
        if (entityLeague != null) {
            throw new IllegalArgumentException("Entity exists!");
        }
        
        return leagueRepository.save(league);
    }

    @Override
    public League update(League league) {
        boolean exists = leagueRepository.existsById(league.getId());
        
        if (!exists) {
            throw new EntityNotFoundException("Entity does not exist!");
        }
        
        return leagueRepository.save(league);        
    }

    @Override
    public League addTeam(Long leagueId, Team team) {
        League entityLeague = leagueRepository.findById(leagueId)
                .orElseThrow(()-> new NotFoundException("League entity does not exists!"));
        
        Team entityTeam = teamRepository.findById(team.getId())
                .orElseThrow(()-> new NotFoundException("Team entity does not exists!"));
        
        boolean conttains = entityLeague.getTeams().contains(entityTeam);
        if (conttains) {
            return entityLeague;
        }
        entityLeague.getTeams().add(entityTeam);
        return leagueRepository.save(entityLeague);
    }

    @Override
    public League addTeam(Long leagueId, Long teamId) {
        League entityLeague = leagueRepository.findById(leagueId)
                .orElseThrow(()-> new NotFoundException("League entity does not exists!"));
        
        Team entityTeam = teamRepository.findById(teamId)
                .orElseThrow(()-> new NotFoundException("Team entity does not exists!"));
        
        boolean conttains = entityLeague.getTeams().contains(entityTeam);
        if (conttains) {
            return entityLeague;
        }
        entityLeague.getTeams().add(entityTeam);
        return leagueRepository.save(entityLeague);
    }
    
    
    @Override
    public void delete(League league) {
        leagueRepository.delete(league);
    }

    @Override
    public void delete(Long leagueId) {
        leagueRepository.deleteById(leagueId);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<League> getAll(Pageable pageable) {
        return leagueRepository.findAll(pageable);
    }

    @Override
    public League get(Long id) {
        return leagueRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException());
    }

    @Override
    public League create(League league) {
        League entityLeague = leagueRepository
                .findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), 
                        league.getName(), league.getRank())
                .orElse(null);
        
        if (entityLeague != null) {
            throw new EntityExistsException("Entity exists!");
        }
        
        return leagueRepository.save(league);
    }

    @Override
    public League update(Long id, League league) {
        boolean exists = leagueRepository.existsById(id);
        
        if (!exists) {
            throw new EntityNotFoundException("Entity does not exist!");
        }
        
        return leagueRepository.save(league);        
    }

    @Override
    public League addTeams(Long leagueId, Set<Long> teamIds) {
        League league = leagueRepository.findById(leagueId).orElseThrow(()-> new EntityNotFoundException());
        
        List<Team> inTeams = teamRepository.findAllById(teamIds);
        if (inTeams.size() < teamIds.size()) {
            var enityIds = inTeams.stream()
                    .map((team) -> team.getId())
                    .toList();
            var invalidIds = teamIds.stream()
                    .filter((id) -> !enityIds.contains(id))
                    .collect(Collectors.toList());

            throw new IllegalArgumentException(String.format("The following IDs are invalid: %s", invalidIds));
        }
        league.getTeams().addAll(inTeams);
        return leagueRepository.save(league);
    }

    @Override
    public League removeTeams(Long leagueId, Set<Long> teamIds) {
        League league = leagueRepository.findById(leagueId).orElseThrow(()-> new EntityNotFoundException());
        var outTeams = league.getTeams().stream()
                .filter((team) -> teamIds.contains(team.getId()))
                .collect(Collectors.toList());
        league.getTeams().removeAll(outTeams);
        return leagueRepository.save(league);
    }

    @Override
    public League replaceTeams(Long leagueId, Set<Long> teamIds) {
        League league = leagueRepository.findById(leagueId).orElseThrow(()-> new EntityNotFoundException());
        
        List<Team> inTeams = teamRepository.findAllById(teamIds);
        
        if (inTeams.size() < teamIds.size()) {
            var enityIds = inTeams.stream()
                    .map((team) -> team.getId())
                    .toList();
            var invalidIds = teamIds.stream()
                    .filter((id) -> !enityIds.contains(id))
                    .collect(Collectors.toList());

            throw new IllegalArgumentException(String.format("The following IDs are invalid: %s", invalidIds));
        }
        
        league.setTeams(new HashSet<Team>(inTeams));
        return leagueRepository.save(league);
    }

    @Override
    public void delete(Long leagueId) {
        leagueRepository.deleteById(leagueId);
    }
    
}

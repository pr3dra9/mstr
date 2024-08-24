/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.repository.MatchRepository;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository repo;
    
    @Override
    public Match create(Match match) {
        Match dbMatch = repo.findByLeagueAndHomeTeamAndAwayTeamAndRound(match.getLeague(), match.getHomeTeam(), match.getAwayTeam(), match.getRound())
                .orElse(null);
        
        if (dbMatch != null) {
            throw new EntityExistsException("Entity exists!");
        }
        
        return repo.save(match);
    }

    @Override
    public List<Match> getAll() {
        return repo.findAll();
    }

    @Override
    public Match getById(Long matchId) {
        return repo.findById(matchId).orElse(null);
    }

    @Override
    public List<Match> getAllForPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return repo.findMatchesBetweenDates(startDateTime, endDateTime);
    }

    @Override
    public List<Match> getByIds(List<Long> ids) {
        return repo.findByIds(ids);
    }

    @Override
    public Match update(Match match) {
        boolean exists = repo.existsById(match.getId());
        if (!exists) {
            throw new EntityNotFoundException("Entity does not exist!");
        }
        return repo.save(match);
    }

    @Override
    public void delete(Match match) {
        repo.deleteById(match.getId());
    }

    @Override
    public Match findMatch(String leagueRegion, String leagueName, String round, String homeTeamName) {
        List<Match> matches = repo.findByLeagueRegionAndLeagueNameAndRoundAndHomeTeamName(leagueRegion, leagueName, round, homeTeamName);
        
        if (matches.isEmpty())
            return null;
        return matches.get(0);
    }
    
}

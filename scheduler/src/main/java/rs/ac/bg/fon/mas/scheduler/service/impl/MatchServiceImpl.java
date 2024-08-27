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
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.MatchMassage;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchOutcome;
//import rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
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
    
    @Autowired
    private StreamBridge streamBridge;
    
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
    public Page<Match> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Match getById(Long matchId) {
        return repo.findById(matchId)
                .orElseThrow(()-> new EntityNotFoundException());
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
    public Match update(Long id, Match match) {
        boolean exists = repo.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Entity does not exist!");
        }
        return repo.save(match);
    }

    @Override
    public Match toComplited(Match match) {
        boolean exists = repo.existsById(match.getId());
        if (!exists) {
            throw new EntityNotFoundException("Entity does not exist!");
        }
        match.setStatus(MatchStatus.COMPLETED);
        Match entity = repo.save(match);        
        
        sendMessage(entity);
        
        return entity;
    }
    
    private void sendMessage(Match match) {
        streamBridge.send(
                "matchCompletion-out-0", 
                new MatchMassage(
                        match.getId(), 
                        getMatchOutcome(match)));    
    }
    
    private MatchOutcome getMatchOutcome(Match match) {
       MatchOutcome mo = MatchOutcome.DRAW;
        if (match.getAwayTeamGoals() > match.getHomeTeamGoals()) {
            mo = MatchOutcome.AWAY_WIN;
        } else if (match.getHomeTeamGoals() > match.getAwayTeamGoals()) {
            mo = MatchOutcome.HOME_WIN;
        }
        return mo;
    }
    
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Match findMatch(String leagueRegion, String leagueName, String round, String homeTeamName) {
        List<Match> matches = repo.findByLeagueRegionAndLeagueNameAndRoundAndHomeTeamName(leagueRegion, leagueName, round, homeTeamName);
        
        if (matches.isEmpty())
            return null;
        return matches.get(0);
    }
    
}

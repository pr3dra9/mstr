/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.mas.scheduler.model.Match;

/**
 *
 * @author Predrag
 */
public interface MatchService {
    Match create(Match match);
    Page<Match> getAll(Pageable pageable);
    Match getById(Long matchId);
    List<Match> getAllForPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Match> getByIds(List<Long> ids);
    Match findMatch(String leagueRegion, String leagueName, String round, String homeTeamName);
    Match update(Long id, Match match);
    Match toComplited(Match match);
    void delete(Long id);
}

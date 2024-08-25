/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import java.time.LocalDateTime;
import java.util.List;
import rs.ac.bg.fon.mas.scheduler.model.Match;

/**
 *
 * @author Predrag
 */
public interface MatchService {
    Match create(Match match);
    List<Match> getAll();
    Match getById(Long matchId);
    List<Match> getAllForPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Match> getByIds(List<Long> ids);
    Match update(Match match);
    Match toComplited(Match match);
    void delete(Match match);
    Match findMatch(String leagueRegion, String leagueName, String round, String homeTeamName);
}

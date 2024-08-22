/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import java.util.List;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;

/**
 *
 * @author Predrag
 */
public interface MatchEventService {
    MatchEvent create(MatchEvent event);
    List<MatchEvent> getAllForMatch(Match match);
}

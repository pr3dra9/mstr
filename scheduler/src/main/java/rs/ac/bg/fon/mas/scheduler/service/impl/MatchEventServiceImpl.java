/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityExistsException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;
import rs.ac.bg.fon.mas.scheduler.repository.MatchEventRepository;
import rs.ac.bg.fon.mas.scheduler.service.MatchEventService;

/**
 *
 * @author Predrag
 */
@Service
public class MatchEventServiceImpl implements MatchEventService{

    @Autowired
    MatchEventRepository repo;
    
    @Override
    public MatchEvent create(MatchEvent mEvent) {
        List<MatchEvent> events = repo.findByMatchOrderByMinute(mEvent.getMatch());
        boolean exists = false;
        for (MatchEvent event : events) {
            if (event.equals(mEvent)) {
                exists = true;
                break;
            }
        }
        if (exists)
            throw new EntityExistsException();
        
        return repo.save(mEvent);
    }

    @Override
    public List<MatchEvent> getAllForMatch(Match match) {
        return repo.findByMatchOrderByMinute(match);
    }
    
}

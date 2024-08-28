/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;
import rs.ac.bg.fon.mas.match_feed.repository.EventCounterRepository;
import rs.ac.bg.fon.mas.match_feed.service.EventCounterService;

/**
 *
 * @author Predrag
 */
@Service
public class EventCounterServiceImpl implements EventCounterService {
    private static final Logger logger = LoggerFactory.getLogger(EventCounterServiceImpl.class);
    
    @Autowired
    EventCounterRepository repo;
    
    @Override
    public int getCounter(Long uuid) {
        logger.trace("getSentEventCount -> uuid: " + uuid);
        EventCounter counter = repo.findByUuid(uuid).orElse(null);
        if (counter == null)
            return 0;
        return counter.getCounter();
    }
    
    @Override
    public EventCounter saveEventCounter(EventCounter counter) {
        logger.trace("saveEventCount -> counter: " + counter.toString());
        EventCounter dbCounter = repo.findByUuid(counter.getUuid()).orElse(null);
        if (dbCounter == null) {
            return repo.save(counter);
        }
        dbCounter.setCounter(counter.getCounter());
        return repo.save(dbCounter);
    }

    @Override
    public boolean isFinished(Long uuid) {
        logger.trace("isFinished -> uuid: " + uuid);
        EventCounter counter = repo.findByUuid(uuid).orElse(null);
        if (counter == null)
            return false;
        return counter.isFinished();
    }
    
    @Override
    public boolean setFinished(Long uuid) {
        logger.trace("setFinished -> uuid: " + uuid);
        EventCounter counter = repo.findByUuid(uuid).orElse(null);
        if (counter == null) {
            counter = new EventCounter(uuid, 0, true);
        } else {
            counter.setFinished(true);
        }
        repo.save(counter);
        return counter.isFinished();
    }
    
}

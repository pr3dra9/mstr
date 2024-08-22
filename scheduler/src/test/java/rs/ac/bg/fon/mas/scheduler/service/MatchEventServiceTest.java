/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchEventType;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.repository.MatchEventRepository;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
public class MatchEventServiceTest {
    
    @Autowired
    MatchEventService service;
    
    @MockBean
    MatchEventRepository repo;
    
    
    private MatchEvent entityMatchEvent1;
    private MatchEvent entityMatchEvent2;
    private MatchEvent entityMatchEvent3;
    private MatchEvent matchEvent;
    private MatchEvent entityMatchEvent4;
    
    private Match entityMatch;
    
    private League leagueEntity;
    private Team arsenalEntity;
    private Team chelseaEntity;
    
    @BeforeEach
    public void setUp() {
        arsenalEntity = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        chelseaEntity = new Team(2L, "Chelea", "che.png", "England", "London", "Stamford Bridge");
        leagueEntity = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, List.of(arsenalEntity, chelseaEntity));

        entityMatch = new Match(1L, leagueEntity, arsenalEntity, chelseaEntity, 1, 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);

        entityMatchEvent1 = new MatchEvent(1L, entityMatch, MatchEventType.GOAL, arsenalEntity, "Ronaldo", 55, "Cross");
        entityMatchEvent2 = new MatchEvent(2L, entityMatch, MatchEventType.YELLOW_CARD, arsenalEntity, "Ronaldo", 85, "Cross");
        entityMatchEvent3 = new MatchEvent(3L, entityMatch, MatchEventType.SUBSTITUTION, arsenalEntity, "Ronaldo", 90, "Cross");
        matchEvent = new MatchEvent(entityMatch, MatchEventType.FULLTIME, null, "", 95, "");
        entityMatchEvent4 = new MatchEvent(4L, entityMatch, MatchEventType.FULLTIME, null, "", 95, "");
    }
    
    @Test
    public void testCreate() {
        Mockito.when(repo.findByMatchOrderByMinute(entityMatch))
                .thenReturn(List.of(entityMatchEvent1, entityMatchEvent2, entityMatchEvent3));
    
        Mockito.when(repo.save(matchEvent))
                .thenReturn(entityMatchEvent4);
        
        MatchEvent dbMatchEvent = service.create(matchEvent);
        
        assertNotNull(dbMatchEvent);
        assertNotNull(dbMatchEvent.getId());
        
        Mockito.verify(repo).findByMatchOrderByMinute(entityMatch);

        Mockito.verify(repo).save(matchEvent);
    }

    @Test
    public void testCreate_throwExcepsion_entityExists() {
        Mockito.when(repo.findByMatchOrderByMinute(entityMatch))
                .thenReturn(List.of(entityMatchEvent1, entityMatchEvent2, entityMatchEvent3));
    
        assertThrows(EntityExistsException.class, () -> {
            service.create(entityMatchEvent3);
        });
        
        Mockito.verify(repo).findByMatchOrderByMinute(entityMatch);

        Mockito.verify(repo, times(0)).save(matchEvent);
    }

    
    @Test
    public void testGetAllForMatch() {
        Mockito.when(repo.findByMatchOrderByMinute(entityMatch))
                .thenReturn(List.of(entityMatchEvent1, entityMatchEvent2, entityMatchEvent3));
    
        List<MatchEvent> dbEvents = service.getAllForMatch(entityMatch);
        Assertions.assertEquals(3, dbEvents.size());
        
        Mockito.verify(repo).findByMatchOrderByMinute(entityMatch);
        
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchEventType;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatchEventRepositoryTest {

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    LeagueRepository leagueRepo;

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    MatchEventRepository matchEventRepo;

    private Team arsenalEntity;
    private Team chelseaEntity;
    private League leagueEntity;
    private Match entityMatch;

    @BeforeAll
    public void setUp() {
        Team arsenal = new Team(0L, "Arsenal", "ars.png", "England", "London", "Emirates");
        arsenalEntity = teamRepo.save(arsenal);

        Team chelsea = new Team(0L, "Chelsea", "che.png", "England", "London", "Stamford Bridge");
        chelseaEntity = teamRepo.save(chelsea);

        League league = new League(0L, "England", 1, "2024-25", "Premier League", "pl.png", 38, List.of(arsenalEntity, chelseaEntity));
        leagueEntity = leagueRepo.save(league);

        Match match = new Match(0L, leagueEntity, arsenalEntity, chelseaEntity, 1, LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        entityMatch = matchRepo.save(match);
    }

    @AfterAll
    public void tearDownAll() {
        matchRepo.delete(entityMatch);
        leagueRepo.delete(leagueEntity);
        teamRepo.delete(chelseaEntity);
        teamRepo.delete(arsenalEntity);
    }

    @Test
    public void testMatchEventSave() {
        MatchEvent matchEvent
                = new MatchEvent(null, entityMatch, MatchEventType.GOAL, arsenalEntity, "Ronaldo", 60, "Cross, headshot, goal");

        MatchEvent savedMatchEvent = null;
        try {
            savedMatchEvent = matchEventRepo.save(matchEvent);

            assertNotNull(savedMatchEvent.getId(), "MatchEvent ID should not be null");

            assertEquals(MatchEventType.GOAL, savedMatchEvent.getType(), "EventType should be GOAL");
            assertEquals(arsenalEntity, savedMatchEvent.getTeam(), "Team should be Arsenal");
            assertEquals("Ronaldo", savedMatchEvent.getPlayer(), "PlayerName should be Ronaldo");
            assertEquals(60, savedMatchEvent.getMinute(), "Minute should be 60");
            assertEquals("Cross, headshot, goal", savedMatchEvent.getDescription(), "Description should match");

            assertEquals(entityMatch.getId(), savedMatchEvent.getMatch().getId(), "Match ID should match");
        } finally {
            if (savedMatchEvent != null) {
                matchEventRepo.delete(savedMatchEvent);
            }
        }

    }

    @Test
    public void testFindByMatchOrderByMinute() {
        MatchEvent matchEvent1
                = new MatchEvent(entityMatch, MatchEventType.GOAL, arsenalEntity, "Ronaldo", 60, "Cross, headshot, goal");

        MatchEvent matchEvent2
                = new MatchEvent(entityMatch, MatchEventType.GOAL, arsenalEntity, "Ronaldo", 50, "Cross, headshot, goal");

        MatchEvent matchEvent3
                = new MatchEvent(entityMatch, MatchEventType.GOAL, arsenalEntity, "Ronaldo", 75, "Cross, headshot, goal");

        List<MatchEvent> savedEvents = null; 
        try {
            savedEvents = matchEventRepo.saveAll(List.of(matchEvent1, matchEvent2, matchEvent3));
        
            List<MatchEvent> events =  matchEventRepo.findByMatchOrderByMinute(entityMatch);
            
            assertEquals(3, events.size());
            assertEquals(50, events.get(0).getMinute());
            assertEquals(60, events.get(1).getMinute());
            assertEquals(75, events.get(2).getMinute());
        
        } finally {
            if (savedEvents != null) {
                matchEventRepo.deleteAll(savedEvents);
            }
        }
        
    }

}

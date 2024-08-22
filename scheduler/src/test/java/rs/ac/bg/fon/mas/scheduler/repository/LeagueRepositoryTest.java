/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */

@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeagueRepositoryTest {
    
    @Autowired
    LeagueRepository leagueRepo;

    @Autowired
    TeamRepository teamRepo;
    
    private Team arsenalEntity;
    private Team chelseaEntity;
    
    @BeforeAll
    public void setUp() {
        Team arsenal = new Team("Arsenal", "ars.png", "England", "London", "Emirates");
        arsenalEntity = teamRepo.save(arsenal);

        Team chelsea = new Team("Chelsea", "che.png", "England", "London", "Stamford Bridge");
        chelseaEntity = teamRepo.save(chelsea);
    }

    @AfterAll
    public void tearDownAll() {
        teamRepo.delete(chelseaEntity);
        teamRepo.delete(arsenalEntity);
    }

    @Test
    public void testLeagueSave_withTeams() {        
        League league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, List.of(arsenalEntity, chelseaEntity));
        League savedLeague = null;
        try{
            savedLeague = leagueRepo.save(league);

            assertNotNull(savedLeague.getId(), "League ID should not be null");
            assertEquals("England", savedLeague.getRegion(), "Reagion should be England");
            assertEquals(1, savedLeague.getRank(), "Rank should be 1");
            assertEquals("Premier League", savedLeague.getName(), "Name should be Premier League");
            assertEquals("pl.png", savedLeague.getLogo(), "Logo should be pl.png");
            assertEquals(38, savedLeague.getRounds(), "Rounds should be 38");
            assertEquals(2, savedLeague.getTeams().size(), "Team size should be 2");
            assertEquals("2024-25", savedLeague.getSeason(), "Season should be 2024-25");            
        } finally {
            if (savedLeague != null)
                leagueRepo.delete(savedLeague);
        }
        
    }
    
    @Test
    public void testLeagueRead_withTeams() {        
        League league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, List.of(arsenalEntity, chelseaEntity));
        League savedLeague = null;
        try{
            savedLeague = leagueRepo.save(league);

            savedLeague = leagueRepo.findById(savedLeague.getId()).orElse(null);
            
            assertNotNull(savedLeague);
            assertEquals("England", savedLeague.getRegion(), "Reagion should be England");
            assertEquals(1, savedLeague.getRank(), "Rank should be 1");
            assertEquals("Premier League", savedLeague.getName(), "Name should be Premier League");
            assertEquals("pl.png", savedLeague.getLogo(), "Logo should be pl.png");
            assertEquals(38, savedLeague.getRounds(), "Rounds should be 38");
            assertEquals(2, savedLeague.getTeams().size(), "Team size should be 2");
            assertEquals("2024-25", savedLeague.getSeason(), "Season should be 2024-25");            
        } finally {
            if (savedLeague != null)
                leagueRepo.delete(savedLeague);
        }
        
    }
        
    @Test
    public void testLeagueSave_withOutTeams() {        
        League league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, List.of());
        League savedLeague = null;
        try{
            savedLeague = leagueRepo.save(league);

            assertNotNull(savedLeague.getId(), "League ID should not be null");
            assertEquals("England", savedLeague.getRegion(), "Reagion should be England");
            assertEquals(1, savedLeague.getRank(), "Rank should be 1");
            assertEquals("Premier League", savedLeague.getName(), "Name should be Premier League");
            assertEquals("pl.png", savedLeague.getLogo(), "Logo should be pl.png");
            assertEquals(38, savedLeague.getRounds(), "Rounds should be 38");
            assertEquals(0, savedLeague.getTeams().size(), "Team size should be 0");
            assertEquals("2024-25", savedLeague.getSeason(), "Season should be 2024-25");            
        } finally {
            if (savedLeague != null)
                leagueRepo.delete(savedLeague);
        }
        
    }
    
    @Test
    public void testLeagueEdit() {        
        League league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, new ArrayList<>());
        League savedLeague = null;
        try{
            savedLeague = leagueRepo.save(league);
                        
            savedLeague.setLogo("pl1.png");
            savedLeague.setName("Premier League 1");
            savedLeague.setRank(2);
            savedLeague.setRegion("England 1");
            savedLeague.setRounds(39);
            savedLeague.getTeams().add(arsenalEntity);
            savedLeague.getTeams().add(chelseaEntity);
            savedLeague.setSeason("2025-26");
            
            savedLeague = leagueRepo.save(savedLeague);
            
            assertEquals("England 1", savedLeague.getRegion(), "Reagion should be England 1");
            assertEquals("Premier League 1", savedLeague.getName(), "Name should be Premier League 1");
            assertEquals("pl1.png", savedLeague.getLogo(), "Logo should be pl1.png");
            assertEquals(39, savedLeague.getRounds(), "Rounds should be 39");
            assertEquals(2, savedLeague.getRank(), "Rank should be 2");
            assertEquals(2, savedLeague.getTeams().size(), "Team size should be 2");
            assertEquals("2025-26", savedLeague.getSeason(), "Season should be 2025-26");    
        } finally {
            if (savedLeague != null)
                leagueRepo.delete(savedLeague);
        }
        
    }
    
    @Test
    public void testLeagueDelete() {        
        League league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, List.of());
        League savedLeague = null;
        try{
            savedLeague = leagueRepo.save(league);

            assertNotNull(savedLeague.getId(), "League ID should not be null");

            leagueRepo.delete(savedLeague);
            
            boolean exists = leagueRepo.existsById(savedLeague.getId());
            assertFalse(exists);
        } finally {
            if (savedLeague != null)
                leagueRepo.delete(savedLeague);
        }
        
    }

}

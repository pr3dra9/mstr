/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.repository.LeagueRepository;
import rs.ac.bg.fon.mas.scheduler.repository.TeamRepository;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeagueServiceTest {
    
    @MockBean
    LeagueRepository leagueRepository;

    @MockBean
    TeamRepository teamRepository;
    
    @Autowired
    LeagueService leagueService;
    
    private League league;
    private League entityLeague;
    private League entityLeagueOneTeam;
    private League editedLeague;
    private Team entityTeam;
    
    @BeforeAll
    public void setUp() {
        league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, List.of());
        entityLeague = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, new ArrayList<>());
        editedLeague = new League(1L, "England_edited", 2, "2024-25_edited", "Premier League_edited", "pl_edited.png", 39, List.of());
        entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");        
        entityLeagueOneTeam = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, List.of(entityTeam));
    }

    @Test
    public void testGetAll() {
        Mockito.when(leagueRepository.findAll()).thenReturn(List.of(entityLeague));
        
        List<League> leagues = leagueService.getAll();
        
        Assertions.assertEquals(1, leagues.size());
        
        Mockito.verify(leagueRepository).findAll();
    }
    
    @Test
    public void testGetById() {
        Mockito.when(leagueRepository.findById(anyLong())).thenReturn(Optional.of(entityLeague));
        
        League dbLeague = leagueService.get(1L);
        
        Assertions.assertNotNull(dbLeague);
        
        Mockito.verify(leagueRepository).findById(anyLong());
    }
    
    @Test
    public void testGetById_NoLeague() {
        Mockito.when(leagueRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        League dbLeague = leagueService.get(1L);
        
        Assertions.assertNull(dbLeague);
        
        Mockito.verify(leagueRepository).findById(anyLong());
    }
    
    @Test
    public void testCreate() {
        Mockito.when(leagueRepository.save(league)).thenReturn(entityLeague);
        Mockito.when(leagueRepository.findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), league.getName(), 1))
                .thenReturn(Optional.empty());
        
        League dbLeague = leagueService.create(league);
        
        Assertions.assertNotNull(dbLeague);
        
        Assertions.assertNotNull(dbLeague.getId());
        
        Assertions.assertEquals("pl.png", dbLeague.getLogo());
        Assertions.assertEquals("Premier League", dbLeague.getName());
        Assertions.assertEquals(Integer.valueOf(1), dbLeague.getRank());
        Assertions.assertEquals("England", dbLeague.getRegion());
        Assertions.assertEquals(Integer.valueOf(38), dbLeague.getRounds());
        Assertions.assertEquals("2024-25", dbLeague.getSeason());
        
        Mockito.verify(leagueRepository).findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), league.getName(), 1);
        Mockito.verify(leagueRepository).save(league);
    }

    @Test
    public void testCreate_throwsException_entityExist() {
        Mockito.when(leagueRepository.findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), league.getName(), 1))
                .thenReturn(Optional.of(entityLeague));
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            leagueService.create(league);
        });
        
        Mockito.verify(leagueRepository).findByRegionAndSeasonAndNameAndRank(league.getRegion(), league.getSeason(), league.getName(), 1);
        
    }

    @Test
    public void testUpdate() {
        Mockito.when(leagueRepository.existsById(anyLong()))
                .thenReturn(Boolean.TRUE);
        
        Mockito.when(leagueRepository.save(editedLeague))
                .thenReturn(editedLeague);
        
        League dbLeague = leagueService.update(editedLeague);
        
        Assertions.assertNotNull(dbLeague);
        
        Assertions.assertNotNull(dbLeague.getId());
        Assertions.assertEquals("pl_edited.png", dbLeague.getLogo());
        Assertions.assertEquals("Premier League_edited", dbLeague.getName());
        Assertions.assertEquals(Integer.valueOf(2), dbLeague.getRank());
        Assertions.assertEquals("England_edited", dbLeague.getRegion());
        Assertions.assertEquals(Integer.valueOf(39), dbLeague.getRounds());
        Assertions.assertEquals("2024-25_edited", dbLeague.getSeason());
        
        Mockito.verify(leagueRepository).existsById(anyLong());
        Mockito.verify(leagueRepository).save(editedLeague);
    }
    
    @Test
    public void testUpdate_throwsException_entityDoesntExist() {
        Mockito.when(leagueRepository.existsById(anyLong()))
                .thenReturn(Boolean.FALSE);

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            leagueService.update(editedLeague);
        });
        
        Mockito.verify(leagueRepository).existsById(anyLong());
        
    }    

    @Test
    public void testAddTeam() {
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityLeague));

        Mockito.when(teamRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityTeam));

        Mockito.when(leagueRepository.save(entityLeagueOneTeam))
                .thenReturn(entityLeagueOneTeam);
        
        try{
            League dbLeague = leagueService.addTeam(1L, entityTeam);
            Assertions.assertEquals(1, dbLeague.getTeams().size());        
        }finally{
            entityLeague.getTeams().clear();
        }
        
        Mockito.verify(leagueRepository).findById(anyLong());
        Mockito.verify(teamRepository).findById(anyLong());
        Mockito.verify(leagueRepository).save(entityLeagueOneTeam);
        
        
        
    }

    @Test
    public void testAddTeam_teamExists() {
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityLeagueOneTeam));

        Mockito.when(teamRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityTeam));

        Mockito.when(leagueRepository.save(entityLeagueOneTeam))
                .thenReturn(entityLeagueOneTeam);
        
        
        try{
            League dbLeague = leagueService.addTeam(1L, entityTeam);
            Assertions.assertEquals(1, dbLeague.getTeams().size());
        }finally{
            entityLeague.getTeams().clear();
        }
            
        
        
        Mockito.verify(leagueRepository).findById(anyLong());
        Mockito.verify(teamRepository).findById(anyLong());
        Mockito.verify(leagueRepository, times(0)).save(entityLeagueOneTeam);
        
        
    }

    @Test
    public void testAddTeamById() {
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityLeague));

        Mockito.when(teamRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityTeam));

        Mockito.when(leagueRepository.save(entityLeagueOneTeam))
                .thenReturn(entityLeagueOneTeam);
        
        
        League dbLeague = leagueService.addTeam(1L, anyLong());
        Assertions.assertEquals(1, dbLeague.getTeams().size());
        
        Mockito.verify(leagueRepository).findById(anyLong());
        Mockito.verify(teamRepository).findById(anyLong());
        Mockito.verify(leagueRepository).save(entityLeagueOneTeam);
        
        
    }

    @Test
    public void testAddTeamById_teamExists() {
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityLeagueOneTeam));

        Mockito.when(teamRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityTeam));

        Mockito.when(leagueRepository.save(entityLeagueOneTeam))
                .thenReturn(entityLeagueOneTeam);
        
        
        League dbLeague = leagueService.addTeam(1L, anyLong());
        Assertions.assertEquals(1, dbLeague.getTeams().size());
        
        Mockito.verify(leagueRepository).findById(anyLong());
        Mockito.verify(teamRepository).findById(anyLong());
        Mockito.verify(leagueRepository, times(0)).save(entityLeagueOneTeam);
        
        
    }

    @Test
    public void testDelete() {
        Mockito.doNothing().when(leagueRepository).delete(entityLeague);
        
        leagueService.delete(entityLeague);
       
        Mockito.verify(leagueRepository).delete(entityLeague);
    }

    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(leagueRepository).deleteById(anyLong());
        
        leagueService.delete(anyLong());
       
        Mockito.verify(leagueRepository).deleteById(anyLong());
    }
    
}

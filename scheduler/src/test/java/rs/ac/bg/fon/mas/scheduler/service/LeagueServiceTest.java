/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.repository.LeagueRepository;
import rs.ac.bg.fon.mas.scheduler.repository.TeamRepository;
import rs.ac.bg.fon.mas.scheduler.service.impl.LeagueServiceImpl;

/**
 *
 * @author Predrag
 */
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeagueServiceTest {
    
    @Mock
    LeagueRepository leagueRepository;

    @Mock
    TeamRepository teamRepository;
    
    @InjectMocks
    LeagueServiceImpl leagueService;
    
    private League league;
    private League entityLeague;
    private League entityLeagueOneTeam;
    private League editedLeague;
    private Team entityTeam;
    
    @BeforeAll
    public void setUp() {
        league = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, Set.of());
        entityLeague = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, new HashSet<>());
        editedLeague = new League(1L, "England_edited", 2, "2024-25_edited", "Premier League_edited", "pl_edited.png", 39, Set.of());
        entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");        
        entityLeagueOneTeam = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, Set.of(entityTeam));
    }

    @Test
    public void testGetAll() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<League> leaguePage = new PageImpl<>(List.of(entityLeague));
        Mockito.when(leagueRepository.findAll(pageable)).thenReturn(leaguePage);
        
        Page<League> leagues = leagueService.getAll(pageable);
        
        Assertions.assertEquals(1, leagues.getContent().size());
        
        Mockito.verify(leagueRepository).findAll(pageable);
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
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            leagueService.get(1L);
        });
        
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
        
        Assertions.assertThrows(EntityExistsException.class, () -> {
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
        
        League dbLeague = leagueService.update(editedLeague.getId(), editedLeague);
        
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
            leagueService.update(editedLeague.getId(), editedLeague);
        });
        
        Mockito.verify(leagueRepository).existsById(anyLong());
        
    }    

    @Test
    public void testAddTeams() {
        Mockito.when(leagueRepository.findById(anyLong()))
                .thenReturn(Optional.of(entityLeague));

        Mockito.when(teamRepository.findAllById(Set.of(entityTeam.getId())))
                .thenReturn(List.of(entityTeam));

        Mockito.when(leagueRepository.save(entityLeagueOneTeam))
                .thenReturn(entityLeagueOneTeam);
        
        try{
            League dbLeague = leagueService.addTeams(1L, Set.of(entityTeam.getId()));
            Assertions.assertEquals(1, dbLeague.getTeams().size());        
        }finally{
            entityLeague.getTeams().clear();
        }
        
        Mockito.verify(leagueRepository).findById(anyLong());
        Mockito.verify(teamRepository).findAllById(Set.of(entityTeam.getId()));
        Mockito.verify(leagueRepository).save(entityLeagueOneTeam);
        
        
        
    }

    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(leagueRepository).deleteById(anyLong());
        
        leagueService.delete(anyLong());
       
        Mockito.verify(leagueRepository).deleteById(anyLong());
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.EnableTestBinder;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.MatchMassage;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchOutcome;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.repository.MatchRepository;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false"
})
@EnableTestBinder()
public class MatchServiceTest {
    
    @Autowired
    MatchService service;
    
    //@Autowired
    //private InputDestination input;

    @Autowired
    private OutputDestination output;
    
    @MockBean
    MatchRepository repo;
    
    private Match match;
    private Match entityMatch;
    private Match entityMatchComplited;
    private Match entityMatch2;
    private Match editedMatch;
    
    private League leagueEntity;
    private Team arsenalEntity;
    private Team chelseaEntity;
    
    @BeforeEach
    public void setUp() {
        arsenalEntity = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        chelseaEntity = new Team(2L, "Chelea", "che.png", "England", "London", "Stamford Bridge");
        leagueEntity = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, Set.of(arsenalEntity, chelseaEntity));

        match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        
        entityMatch = new Match(1L, leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED,0 ,0); 

        entityMatchComplited = new Match(1L, leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.COMPLETED, 2, 1);

        entityMatch2 = new Match(2L, leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        
        editedMatch = new Match(1L, leagueEntity, chelseaEntity, arsenalEntity, "2", 
                LocalDateTime.parse("2024-08-23T21:00:00"), MatchStatus.SCHEDULED);
        
        
    }
    
    @Test
    public void testCreate() {
        Mockito.when(repo.findByLeagueAndHomeTeamAndAwayTeamAndRound(match.getLeague(), match.getHomeTeam(),
                match.getAwayTeam(), match.getRound())).thenReturn(Optional.empty());
    
        Mockito.when(repo.save(match)).thenReturn(entityMatch);
        
        Match dbMatch = service.create(match);
        
        assertNotNull(dbMatch);
        
        Mockito.verify(repo).findByLeagueAndHomeTeamAndAwayTeamAndRound(match.getLeague(), match.getHomeTeam(),
                match.getAwayTeam(), match.getRound());
        Mockito.verify(repo).save(match);
    }

    @Test
    public void testCreate_throwException_entityExists() {
        Mockito.when(repo.findByLeagueAndHomeTeamAndAwayTeamAndRound(match.getLeague(), match.getHomeTeam(),
                match.getAwayTeam(), match.getRound())).thenReturn(Optional.of(entityMatch));
        
        assertThrows(EntityExistsException.class, () -> {
            service.create(match);
        });
        
        Mockito.verify(repo).findByLeagueAndHomeTeamAndAwayTeamAndRound(match.getLeague(), match.getHomeTeam(),
                match.getAwayTeam(), match.getRound());
        Mockito.verify(repo, times(0)).save(match);
    }

    @Test
    public void testGetAll() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<Match> leaguePage = new PageImpl<>(List.of(entityMatch));
        
        Mockito.when(repo.findAll(pageable)).thenReturn(leaguePage);
        
        Page<Match> matches = service.getAll(pageable);
        
        Assertions.assertEquals(1, matches.getSize());
        
        Mockito.verify(repo).findAll(pageable);
    }
    
    @Test
    public void testGetAllForPeriod() {
        Mockito.when(repo.findMatchesBetweenDates(LocalDateTime.parse("2024-08-21T00:00:00")
                , LocalDateTime.parse("2024-08-22T00:00:00")))
            .thenReturn(List.of(entityMatch));
        
        List<Match> dbMatches = service.getAllForPeriod(LocalDateTime.parse("2024-08-21T00:00:00"),
                LocalDateTime.parse("2024-08-22T00:00:00"));
        
        Assertions.assertEquals(1, dbMatches.size());
        
        Mockito.verify(repo).findMatchesBetweenDates(LocalDateTime.parse("2024-08-21T00:00:00"),
                LocalDateTime.parse("2024-08-22T00:00:00"));
    }
    
    @Test
    public void testGetByIds() {
        Mockito.when(repo.findByIds(List.of(1L,2L)))
            .thenReturn(List.of(entityMatch, entityMatch2));
        
        List<Match> dbMatches = service.getByIds(List.of(1L,2L));
        Assertions.assertEquals(2, dbMatches.size());
        
        Mockito.verify(repo).findByIds(List.of(1L,2L));
    }
    
    @Test
    public void testUpdate() {
        Mockito.when(repo.existsById(anyLong()))
            .thenReturn(Boolean.TRUE);
        
        Mockito.when(repo.save(editedMatch))
            .thenReturn(editedMatch);
        
        Match dbMatch = service.update(editedMatch.getId(), editedMatch);
        Assertions.assertNotNull(dbMatch);
        Assertions.assertNotNull(dbMatch.getId());
        
        Mockito.verify(repo).existsById(anyLong());
        Mockito.verify(repo).save(editedMatch);
    }

    @Test
    public void testUpdate_throwException_entityDoesNotExists() {
        Mockito.when(repo.existsById(anyLong()))
            .thenReturn(Boolean.FALSE);
        
        assertThrows(EntityNotFoundException.class, () -> {
            service.update(editedMatch.getId(), editedMatch);
        });
        
        Mockito.verify(repo).existsById(anyLong());
        Mockito.verify(repo, times(0)).save(editedMatch);
    }
    
    @Test
    public void testGetById() {
        Mockito.when(repo.findById(anyLong())).thenReturn(Optional.of(entityMatch));
        
        Match dbMatch = service.getById(anyLong());
        assertNotNull(dbMatch);
        
        Mockito.verify(repo).findById(anyLong());
        
    }
    
    @Test
    public void testDelete() {
        Mockito.doNothing().when(repo).deleteById(anyLong());
        
        service.delete(entityMatch.getId());
       
        Mockito.verify(repo).deleteById(anyLong());
    }
    
    @Test
    public void testToComplited() throws IOException {
        Mockito.when(repo.existsById(anyLong()))
            .thenReturn(Boolean.TRUE);
        
        Mockito.when(repo.save(entityMatchComplited))
            .thenReturn(entityMatchComplited);
        
        Assertions.assertEquals(entityMatch, entityMatchComplited);
        
        Match dbMatch = service.toComplited(entityMatch);
        
        Assertions.assertNotNull(dbMatch);
        Assertions.assertNotNull(dbMatch.getId());
        Assertions.assertEquals(MatchStatus.COMPLETED, dbMatch.getStatus());
        
        MatchMassage excpectedMessage = new MatchMassage(
                editedMatch.getId(), 
                rs.ac.bg.fon.mas.scheduler.messaging.dto.enums.MatchStatus.COMPLETED, 
                MatchOutcome.HOME_WIN);
        
        ObjectMapper objectMapper = new ObjectMapper();
        MatchMassage inMessage= objectMapper.readValue(output.receive().getPayload(), MatchMassage.class);
        
        Assertions.assertNotNull(inMessage);
        Assertions.assertEquals(excpectedMessage, inMessage);
        
        Mockito.verify(repo).existsById(anyLong());
        Mockito.verify(repo).save(entityMatchComplited);

    }
    
}

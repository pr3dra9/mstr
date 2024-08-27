/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.LeagueMapperImpl;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.MatchMapperImpl;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.TeamMapperImpl;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.security.config.TestSecurityConfig;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@WebMvcTest(controllers = MatchController.class)
@Import({MatchMapperImpl.class, LeagueMapperImpl.class, TeamMapperImpl.class, TestSecurityConfig.class})
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class MatchControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
        
    @MockBean
    private MatchService service;
    
    ObjectMapper mapper = new ObjectMapper();
    
    private Match match;
    private Match entityMatch;
    private Match entityMatch2;
    private Match editedMatch;
    
    private League leagueEntity;
    private Team arsenalEntity;
    private Team chelseaEntity;
    
    @BeforeEach
    public void setUp() {
        arsenalEntity = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        chelseaEntity = new Team(2L, "Chelea", "che.png", "England", "London", "Stamford Bridge");
        leagueEntity = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of(arsenalEntity, chelseaEntity));

        match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        
        entityMatch = new Match(1L, leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        
        entityMatch2 = new Match(2L, leagueEntity, arsenalEntity, chelseaEntity, "1", 
                LocalDateTime.parse("2024-08-22T21:00:00"), MatchStatus.SCHEDULED);

    }
    
    @Test
    public void testGetAll() throws Exception {
        PageRequest pageReq = PageRequest.of(0, 10);
        var leaguePage = new PageImpl<>(List.of(entityMatch, entityMatch2));
        
        when(service.getAll(pageReq))
                .thenReturn(leaguePage);
        
        this.mockMvc
                .perform(get("/matches"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(2)));
                // .content(mapper.writeValueAsString(entity))
                
        Mockito.verify(service).getAll(pageReq);
    }

    @Test
    public void testGetById() throws Exception {
        
        when(service.getById(anyLong()))
                .thenReturn(entityMatch);
        
        this.mockMvc
                .perform(get("/matches/1"))
                .andExpect(status().isOk());
                // .content(mapper.writeValueAsString(entity))
                
        Mockito.verify(service).getById(anyLong());
    }

    @Test
    public void testGetById_NotFoundCode_EntityDoesNotExist() throws Exception {
        
        when(service.getById(anyLong()))
                .thenReturn(null);
        
        this.mockMvc
                .perform(get("/matches/1"))
                .andExpect(status().isNotFound());
                // .content(mapper.writeValueAsString(entity))
                
        Mockito.verify(service).getById(anyLong());
    }
    
    @Test
    public void testGetByIds() throws Exception {
        when(service.getByIds(List.of(Long.valueOf(1L), Long.valueOf(2L))))
                .thenReturn(List.of(entityMatch, entityMatch2));
        
        this.mockMvc
                .perform(get("/matches/by-ids?ids=1&ids=2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
                // .content(mapper.writeValueAsString(entity))
                
        Mockito.verify(service).getByIds(List.of(Long.valueOf(1L), Long.valueOf(2L)));
    }
    
    @Test
    public void testGetByIds_badRequest_noIds() throws Exception {
        this.mockMvc
                .perform(get("/matches/by-ids"))
                .andExpect(status().isBadRequest());
                
        Mockito.verify(service, Mockito.times(0)).getByIds(anyList());
    }
    
    @Test
    public void testGetAllForPeriod() throws Exception {
        when(service.getAllForPeriod(LocalDateTime.parse("2024-08-21T00:00:00"), LocalDateTime.parse("2024-08-22T00:00:00")))
            .thenReturn(List.of(entityMatch));
        
        this.mockMvc
            .perform(get("/matches/by-period?startDate=2024-08-21T00:00:00&endDate=2024-08-22T00:00:00"))
            .andExpect(status().isOk());
                
        Mockito.verify(service)
                .getAllForPeriod(LocalDateTime.parse("2024-08-21T00:00:00"), LocalDateTime.parse("2024-08-22T00:00:00"));
    }

}

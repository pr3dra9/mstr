/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.LeagueMapperImpl;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.MatchMapperImpl;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.TeamMapperImpl;
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchLeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchTeamDto;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@WebMvcTest(controllers = AdminMatchController.class)
@Import({MatchMapperImpl.class, LeagueMapperImpl.class, TeamMapperImpl.class})
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class AdminMatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MatchService service;

    ObjectMapper mapper = new ObjectMapper();
    
    private LeagueDto dtoLeague;
    private TeamDto dtoPartizan;
    private TeamDto dtoRedStar;

    private MatchDto newDtoDerbi1;
    private MatchDto newDtoDerbi2;
    private MatchDto savedDtoDerbi1;
    private MatchDto editedDtoDerbi1;

    private League entityLeague;
    private Team entityPartizan;
    private Team entityRedStar;

    private Match newEntityDerbi1;
    private Match newEntityDerbi2;
    private Match savedEntityDerbi1;
    private Match savedEntityDerbi2;
    private Match editedEntityDerbi1;
    
    @BeforeEach
    public void setUp() {
        dtoLeague = new LeagueDto(1L, "Serbia", 1, "2024", "Superliga", "fss.png", 37, Set.of());
        dtoPartizan = new TeamDto(11L, "Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");
        dtoRedStar = new TeamDto(12L, "Red Star", "czv.png", "Serbia", "Belgrade", "Marakana");

        MatchLeagueDto dtoMatchLeague = new MatchLeagueDto(1L, "Superliga");
        MatchTeamDto dtoMatchTeamPar = new MatchTeamDto(11L,"Partizan");
        MatchTeamDto dtoMatchTeamCzv = new MatchTeamDto(12L, "Red Star");
        
        newDtoDerbi1 = new MatchDto(null, dtoMatchLeague, dtoMatchTeamPar, dtoMatchTeamCzv, "Regular season - 1", "2024-08-30T15:30:00", "SCHEDULED", 0, 0);
        newDtoDerbi2 = new MatchDto(null, dtoMatchLeague, dtoMatchTeamCzv, dtoMatchTeamPar, "Regular season - 23", "2024-08-30T15:30:00", "SCHEDILED", 0, 0);
        savedDtoDerbi1 = new MatchDto(101L, dtoMatchLeague, dtoMatchTeamPar, dtoMatchTeamCzv, "Regular season - 1", "2024-08-30T15:30:00", "SCHEDULED", 0, 0);
        editedDtoDerbi1 = new MatchDto(101L, dtoMatchLeague, dtoMatchTeamPar, dtoMatchTeamCzv, "Regular season - 2", "2024-09-02T15:30:00", "SCHEDULED", 0, 0);

                
        entityLeague = new League(1L, "Serbia", 1, "2024", "Superliga", "fss.png", 37, Set.of());
        entityPartizan = new Team(11L, "Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");
        entityRedStar = new Team(12L, "Red Star", "czv.png", "Serbia", "Belgrade", "Marakana");

        newEntityDerbi1 = new Match(entityLeague, entityPartizan, entityRedStar, "Regular season - 1", LocalDateTime.now(), MatchStatus.SCHEDULED, 0, 0);
        newEntityDerbi2 = new Match(entityLeague, entityRedStar, entityPartizan, "Regular season - 23", LocalDateTime.now(), MatchStatus.SCHEDULED, 0, 0);
        savedEntityDerbi1 = new Match(101L, entityLeague, entityPartizan, entityRedStar, "Regular season - 1", LocalDateTime.now(), MatchStatus.SCHEDULED, 0, 0);
        savedEntityDerbi2 = new Match(102L, entityLeague, entityRedStar, entityPartizan, "Regular season - 23", LocalDateTime.now(), MatchStatus.SCHEDULED, 0, 0);

        editedEntityDerbi1 = new Match(101L, entityLeague, entityPartizan, entityRedStar, "Regular season - 2", LocalDateTime.now(), MatchStatus.SCHEDULED, 0, 0);
        
        

    }

    @Test
    public void test_getAll() throws Exception {
        PageRequest pageReq = PageRequest.of(0, 2);
        Page<Match> pageDtoMatches = new PageImpl<>(List.of(savedEntityDerbi1, savedEntityDerbi2));

        Mockito.when(service.getAll(pageReq))
                .thenReturn(pageDtoMatches);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/matches")
                .param("page", String.valueOf(pageReq.getPageNumber()))
                .param("size", String.valueOf(pageReq.getPageSize())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(2)));

        verify(service).getAll(pageReq);

    }

    @Test
    public void test_getById() throws Exception {
        Mockito.when(service.getById(savedDtoDerbi1.id()))
                .thenReturn(savedEntityDerbi1);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/matches/{id}", savedDtoDerbi1.id()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(service).getById(savedDtoDerbi1.id());
    }
    
    @Test
    public void test_getById_notFound() throws Exception {
        Mockito.when(service.getById(anyLong()))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/matches/{id}", Long.MAX_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        Mockito.verify(service).getById(Long.MAX_VALUE);
    }

    @Test
    public void test_create() throws Exception {
        Mockito.when(service.create(newEntityDerbi1))
                .thenReturn(savedEntityDerbi1);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newDtoDerbi1))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(service).create(newEntityDerbi1);
    }
    
    @Test
    public void test_create_badRequest() throws Exception {
        Mockito.when(service.create(any(Match.class)))
                .thenThrow(new EntityExistsException());

        mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newDtoDerbi1))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        Mockito.verify(service).create(newEntityDerbi1);
    }
    
    @Test
    public void test_update() throws Exception {
        Mockito.when(service.update(editedDtoDerbi1.id(), editedEntityDerbi1))
                .thenReturn(editedEntityDerbi1);

        mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/matches/{id}", String.valueOf(editedDtoDerbi1.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(editedDtoDerbi1))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(service).update(editedDtoDerbi1.id(), editedEntityDerbi1);
    }

    @Test
    public void test_update_notFound() throws Exception {
        Mockito.when(service.update(anyLong(), any(Match.class)))
                .thenThrow(new EntityNotFoundException());

        mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/matches/{id}", String.valueOf(Long.MAX_VALUE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(editedDtoDerbi1))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        Mockito.verify(service).update(Long.MAX_VALUE, editedEntityDerbi1);
    }

    @Test
    public void test_delete() throws Exception {
        Mockito.doNothing()
                .when(service).delete(editedDtoDerbi1.id());

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/admin/matches/{id}", String.valueOf(editedDtoDerbi1.id())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(service).delete(editedDtoDerbi1.id());
    }
    
}

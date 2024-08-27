/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.LeagueMapperImpl;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.TeamMapperImpl;
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.security.config.TestSecurityConfig;
import rs.ac.bg.fon.mas.scheduler.service.LeagueService;

/**
 *
 * @author Predrag
 */
@WebMvcTest(controllers = AdminLeagueController.class)
@Import({LeagueMapperImpl.class, TeamMapperImpl.class, TestSecurityConfig.class})
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class AdminLeagueControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
        
    @MockBean
    private LeagueService service;
    
    ObjectMapper mapper = new ObjectMapper();
    
    private LeagueDto leagueDtoRes;
    private LeagueDto added_LeagueDtoRes;
    private LeagueDto removed_leagueDtoRes;
    private LeagueDto replaced_LeagueDtoRes;
    
    private LeagueDto leagueDtoReq;
    private LeagueDto editedLeagueDtoReq;
    
    private League leagueReq;
    private League editedLeagueEntityReq;
    private League leagueEntityRes;
    private League added_LeagueEntityRes;
    private League removed_leagueEntityRes;
    private League replaced_LeagueEntityRes;
    
    private Team arsenalEntity;
    private Team chelseaEntity;
    private Team manutdEntity;
    private Team mancityEntity;

    private TeamDto arsenalDto;
    private TeamDto chelseaDto;    
    private TeamDto manutdDto;
    private TeamDto mancityDto;
            
    private final PageRequest pageReq = PageRequest.of(0, 10);

    @BeforeEach
    public void setUp() {
        arsenalEntity = new Team(1L, "Arsenal", "ars.png", "England", "London", "Emirates Stadium");
        chelseaEntity = new Team(2L, "Chelea", "che.png", "England", "London", "Stamford Bridge");
        manutdEntity = new Team(3L, "Manchetser United", "manutd.png", "England", "Manchetser", "Old Trafford");
        mancityEntity = new Team(4L, "Manchetser City", "city.png", "England", "Manchetser", "Etihad Stadium");

        arsenalDto = new TeamDto(1L, "Arsenal", "ars.png", "England", "London", "Emirates Stadium");
        chelseaDto = new TeamDto(2L, "Chelea", "che.png", "England", "London", "Stamford Bridge");
        manutdDto = new TeamDto(3L, "Manchetser United", "manutd.png", "England", "Manchetser", "Old Trafford");
        mancityDto = new TeamDto(4L, "Manchetser City", "city.png", "England", "Manchetser", "Etihad Stadium");

        leagueDtoRes = new LeagueDto(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(arsenalDto, chelseaDto));
        added_LeagueDtoRes = new LeagueDto(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of(arsenalDto, chelseaDto, manutdDto, mancityDto));
        removed_leagueDtoRes = new LeagueDto(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(chelseaDto));
        replaced_LeagueDtoRes = new LeagueDto(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of(manutdDto, mancityDto));
        leagueDtoReq = new LeagueDto(null, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of());

        editedLeagueDtoReq = new LeagueDto(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of());
        
        
        leagueEntityRes = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(arsenalEntity, chelseaEntity));
        added_LeagueEntityRes = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(arsenalEntity, chelseaEntity, manutdEntity, mancityEntity));
        removed_leagueEntityRes = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(chelseaEntity));
        replaced_LeagueEntityRes = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38,
                Set.of(manutdEntity, mancityEntity));
        leagueReq = new League("England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of());        
        editedLeagueEntityReq = new League(1L, "England", 1, "2024-25", "Premier League", "pl.png", 38, 
                Set.of());
        
        
        
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getAll() throws Exception {
        var leaguePage = new PageImpl<>(List.of(leagueEntityRes));
        
        when(service.getAll(pageReq))
                .thenReturn(leaguePage);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/leagues")
                        .param("page", String.valueOf(pageReq.getPageNumber()))
                        .param("size", String.valueOf(pageReq.getPageSize())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(1)));

        verify(service).getAll(pageReq);
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getById() throws Exception {
        when(service.get(anyLong()))
                .thenReturn(leagueEntityRes);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/leagues/{id}", leagueEntityRes.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(1)));

        verify(service).get(leagueEntityRes.getId());
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getById_NotFound() throws Exception {
        when(service.get(anyLong()))
                .thenThrow(new EntityNotFoundException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/leagues/{id}", Long.MAX_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(1)));

        verify(service).get(Long.MAX_VALUE);
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_create() throws Exception {
        when(service.create(leagueReq))
                .thenReturn(leagueEntityRes);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/leagues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(leagueDtoReq))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).create(leagueReq);        
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_create_badRequest() throws Exception {
        when(service.create(any(League.class)))
                .thenThrow(new EntityExistsException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/leagues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(leagueDtoReq))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).create(leagueReq);        
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_update() throws Exception {
        when(service.update(editedLeagueEntityReq.getId(), editedLeagueEntityReq))
                .thenReturn(leagueEntityRes);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}", editedLeagueDtoReq.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(editedLeagueDtoReq))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).update(editedLeagueEntityReq.getId(), editedLeagueEntityReq);   
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_update_notFound() throws Exception {
        when(service.update(anyLong(), any(League.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(editedLeagueDtoReq))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).update(Long.MAX_VALUE, editedLeagueEntityReq);   
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_addTeams() throws Exception  {
        when(service.addTeams(leagueEntityRes.getId(), Set.of(manutdEntity.getId(), mancityEntity.getId())))
                .thenReturn(added_LeagueEntityRes);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/add-teams", leagueDtoRes.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(manutdDto.id(), mancityDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.teams.size()", Matchers.is(4)));
        
        verify(service).addTeams(leagueEntityRes.getId(), Set.of(manutdEntity.getId(), mancityEntity.getId()));  
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_addTeams_badRequest() throws Exception  {
        when(service.addTeams(anyLong(), any(Set.class)))
                .thenThrow(new IllegalArgumentException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/add-teams", leagueDtoRes.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(Long.MAX_VALUE)))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).addTeams(leagueEntityRes.getId(), Set.of(Long.MAX_VALUE));  
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_addTeams_notFound() throws Exception  {
        when(service.addTeams(anyLong(), any(Set.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/add-teams", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(manutdDto.id(), mancityDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).addTeams(Long.MAX_VALUE, Set.of(manutdEntity.getId(), mancityEntity.getId()));  
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_removeTeams() throws Exception {
        when(service.removeTeams(leagueEntityRes.getId(), Set.of(arsenalEntity.getId())))
                .thenReturn(removed_leagueEntityRes);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/remove-teams", leagueDtoRes.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(arsenalDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.teams.size()", Matchers.is(1)));
        
        verify(service).removeTeams(leagueEntityRes.getId(), Set.of(arsenalEntity.getId()));         
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_removeTeams_notFound() throws Exception  {
        when(service.removeTeams(anyLong(), any(Set.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/remove-teams", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(arsenalDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).removeTeams(Long.MAX_VALUE, Set.of(arsenalEntity.getId()));    
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_replaceTeams() throws Exception  {
        when(service.replaceTeams(leagueEntityRes.getId(), Set.of(manutdEntity.getId(), mancityEntity.getId())))
                .thenReturn(replaced_LeagueEntityRes);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/replace-teams", leagueDtoRes.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(manutdDto.id(), mancityDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.teams.size()", Matchers.is(2)));
        
        verify(service).replaceTeams(leagueEntityRes.getId(), Set.of(manutdEntity.getId(), mancityEntity.getId()));  
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_replaceTeams_notFound() throws Exception  {
        when(service.replaceTeams(anyLong(), any(Set.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/replace-teams", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(manutdDto.id(), mancityDto.id())))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).replaceTeams(Long.MAX_VALUE, Set.of(manutdEntity.getId(), mancityEntity.getId()));  
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_replaceTeams_badRequest() throws Exception  {
        when(service.replaceTeams(anyLong(), any(Set.class)))
                .thenThrow(new IllegalArgumentException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/leagues/{id}/replace-teams", leagueDtoRes.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(Set.of(Long.MAX_VALUE)))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).replaceTeams(leagueEntityRes.getId(), Set.of(Long.MAX_VALUE));  
    }
    
}

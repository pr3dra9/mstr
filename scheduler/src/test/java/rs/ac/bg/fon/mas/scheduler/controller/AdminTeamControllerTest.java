/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
import rs.ac.bg.fon.mas.scheduler.controller.mapper.impl.TeamMapperImpl;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.service.TeamService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.mas.scheduler.security.config.TestSecurityConfig;

/**
 *
 * @author Predrag
 */
@WebMvcTest(controllers = AdminTeamController.class)
@Import({TeamMapperImpl.class, TestSecurityConfig.class})
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class AdminTeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TeamService service;

    ObjectMapper mapper = new ObjectMapper();

    private TeamDto dtoTeam1;
    private TeamDto savedDtoTeam1;
    private TeamDto edited_savedDtoTeam1;

    private Team entityTeam1;
    private Team savedEntityTeam1;
    private Team edited_savedEntityTeam1;

    private Team savedEntityTeam2;


    private final PageRequest pageReq = PageRequest.of(0, 2);
    
    @BeforeEach
    public void setUp() {
        dtoTeam1 = new TeamDto(null, "Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");

        savedDtoTeam1 = new TeamDto(11L, "Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");

        edited_savedDtoTeam1 = new TeamDto(11L, "FK Partizan", "fcp.png", "Srbija", "Beograd", "Stadion JNA");

        entityTeam1 = new Team("Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");

        savedEntityTeam1 = new Team(11L, "Partizan", "pfc.png", "Serbia", "Belgrade", "JNA");
        savedEntityTeam2 = new Team(22L, "Red Star", "pfc.png", "Serbia", "Belgrade", "Marakana");

        
        edited_savedEntityTeam1 = new Team(11L, "FK Partizan", "fcp.png", "Srbija", "Beograd", "Stadion JNA");
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getAll() throws Exception {
        var teamPage = new PageImpl<>(List.of(savedEntityTeam1, savedEntityTeam2));
        
        when(service.getAll(pageReq))
                .thenReturn(teamPage);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/teams")
                        .param("page", String.valueOf(pageReq.getPageNumber()))
                        .param("size", String.valueOf(pageReq.getPageSize())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(2)));

        verify(service).getAll(pageReq);
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getById() throws Exception {
        when(service.getById(anyLong()))
                .thenReturn(savedEntityTeam1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/teams/{id}", savedDtoTeam1.id()))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(1)));

        verify(service).getById(savedDtoTeam1.id());
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_getById_notFound() throws Exception {
        when(service.getById(anyLong()))
                .thenThrow(new EntityNotFoundException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/teams/{id}", Long.MAX_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", Matchers.is(1)));

        verify(service).getById(Long.MAX_VALUE);
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_create() throws Exception {
        when(service.create(entityTeam1))
                .thenReturn(savedEntityTeam1);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTeam1))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).create(entityTeam1);   
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_create_badRequest() throws Exception {
        when(service.create(any(Team.class)))
                .thenThrow(new EntityExistsException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTeam1))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).create(entityTeam1);   
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_update() throws Exception {
        when(service.update(edited_savedDtoTeam1.id(), edited_savedEntityTeam1))
                .thenReturn(edited_savedEntityTeam1);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/teams/{id}", edited_savedDtoTeam1.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(edited_savedDtoTeam1))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).update(edited_savedDtoTeam1.id(), edited_savedEntityTeam1); 
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_update_notFound() throws Exception {
        when(service.update(anyLong(), any(Team.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/admin/teams/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(edited_savedDtoTeam1))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).update(Long.MAX_VALUE, edited_savedEntityTeam1); 
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_delete() throws Exception {
        when(service.deleteById(savedDtoTeam1.id()))
                .thenReturn(true);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/admin/teams/{id}", savedDtoTeam1.id()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).deleteById(savedDtoTeam1.id()); 

    }

    @Test
    @WithMockUser(username = "test-user", roles = {"ADMIN"})
    public void test_delete_notFound() throws Exception {
        when(service.deleteById(anyLong()))
                .thenThrow(new EntityNotFoundException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/admin/teams/{id}", Long.MAX_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).deleteById(Long.MAX_VALUE);
    }

}

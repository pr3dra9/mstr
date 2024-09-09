/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.mas.ticketing.config.TestSecurityConfig;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;
import rs.ac.bg.fon.mas.ticketing.dto.PredictionDto;
import rs.ac.bg.fon.mas.ticketing.dto.TicketDto;
import rs.ac.bg.fon.mas.ticketing.mapper.TicketMapper;
import rs.ac.bg.fon.mas.ticketing.mapper.impl.PredictionMapperImpl;
import rs.ac.bg.fon.mas.ticketing.mapper.impl.TicketMapperImpl;
import rs.ac.bg.fon.mas.ticketing.service.TicketService;

/**
 *
 * @author Predrag
 */
@WebMvcTest(controllers = TicketingController.class)
@Import({TicketMapperImpl.class, PredictionMapperImpl.class, TestSecurityConfig.class})
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class TicketingControllerTest {

    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    TicketService service;

    ObjectMapper mapper = new ObjectMapper();

    private final PageRequest pageReq = PageRequest.of(0, 10);

    Ticket entityTicket1;
    Ticket submitedEntityTicket1;
    Ticket entityTicket2;
    Ticket emptyEntityTicket1;
    TicketDto dtoTicket1;
    TicketDto submitedDtoTicket1;

    @BeforeEach
    public void setUp() {

        Prediction p1 = new Prediction(11L, 101L, PredictionOutcome.HOME_WIN, PredictionStatus.ACTIVE);
        Prediction p2 = new Prediction(12L, 102L, PredictionOutcome.DRAW, PredictionStatus.ACTIVE);
        Prediction p3 = new Prediction(13L, 103L, PredictionOutcome.AWAY_WIN, PredictionStatus.ACTIVE);
        entityTicket1 = new Ticket(1L, "test-user", LocalDateTime.parse("2024-09-01T20:00:00"), TicketStatus.DRAFT, List.of(p1, p2, p3));
        submitedEntityTicket1 = new Ticket(1L, "test-user", LocalDateTime.parse("2024-09-01T20:00:00"), TicketStatus.DRAFT, List.of(p1, p2, p3));
        
        emptyEntityTicket1 = new Ticket(1L, "test-user", LocalDateTime.parse("2024-09-01T20:00:00"), TicketStatus.DRAFT, null);

        PredictionDto dtoP1 = new PredictionDto(11L, 101L, "HOME_WIN", "ACTIVE");
        PredictionDto dtoP2 = new PredictionDto(12L, 102L, "DRAW", "ACTIVE");
        PredictionDto dtoP3 = new PredictionDto(13L, 103L, "AWAY_WIN", "ACTIVE");
        dtoTicket1 = new TicketDto(1L, "test-user", "2024-09-01T20:00:00", "DRAFT", List.of(dtoP1, dtoP2, dtoP3));
        submitedDtoTicket1 = new TicketDto(1L, "test-user", "2024-09-01T20:00:00", "DRAFT", List.of(dtoP1, dtoP2, dtoP3));
        
        Prediction p4 = new Prediction(14L, 101L, PredictionOutcome.DRAW, PredictionStatus.ACTIVE);
        Prediction p5 = new Prediction(15L, 102L, PredictionOutcome.AWAY_WIN, PredictionStatus.ACTIVE);
        Prediction p6 = new Prediction(16L, 103L, PredictionOutcome.HOME_WIN, PredictionStatus.ACTIVE);
        entityTicket2 = new Ticket(1L, "test-user", LocalDateTime.parse("2024-09-01T20:00:00"), TicketStatus.DRAFT, List.of(p1, p2, p3));
        
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_getAll() throws Exception {
        //var entityPage = new PageImpl<>(List.of());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.getAll(username))
                .thenReturn(List.of(entityTicket1, entityTicket2));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets"))
                //                        .param("page", String.valueOf(pageReq.getPageNumber()))
                //                        .param("size", String.valueOf(pageReq.getPageSize()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));

        verify(service).getAll(username);
    }

    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_getById() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.getById(username, entityTicket1.getId()))
                .thenReturn(entityTicket1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets/{id}", String.valueOf(entityTicket1.getId())))
                //                        .param("page", String.valueOf(pageReq.getPageNumber()))
                //                        .param("size", String.valueOf(pageReq.getPageSize()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).getById(username, entityTicket1.getId());

    }

    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_getById_notFound() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.getById(anyString(), anyLong()))
                .thenThrow(new EntityNotFoundException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets/{id}", String.valueOf(Long.MAX_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).getById(username, Long.MAX_VALUE);

    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_getByStatus() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.getByStatus(username, TicketStatus.DRAFT.toString().toLowerCase()))
                .thenReturn(List.of(entityTicket1, entityTicket2));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets/by-status/{status}", TicketStatus.DRAFT.toString().toLowerCase()))
                //                        .param("page", String.valueOf(pageReq.getPageNumber()))
                //                        .param("size", String.valueOf(pageReq.getPageSize()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
        
        verify(service).getByStatus(username, TicketStatus.DRAFT.toString().toLowerCase());

    }    
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_getByStatus_badRequest() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.getByStatus(anyString(), anyString()))
                .thenThrow(new IllegalArgumentException());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/tickets/by-status/{status}", "WRONG_STATUS".toLowerCase()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).getByStatus(username, "WRONG_STATUS".toLowerCase());

    }    
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_create() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        when(service.createEmtyTicket(username))
                .thenReturn(emptyEntityTicket1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                //                        .param("page", String.valueOf(pageReq.getPageNumber()))
                //                        .param("size", String.valueOf(pageReq.getPageSize()))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
        
        verify(service).createEmtyTicket(username);

    }    
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_updateTicket() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        when(service.updateDraft(username, entityTicket1.getId(), entityTicket1))
                .thenReturn(entityTicket1);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tickets/{id}", String.valueOf(entityTicket1.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTicket1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.predictions.size()", Matchers.is(3)));
        
        verify(service).updateDraft(username,entityTicket1.getId(), entityTicket1);
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_updateTicket_notFound() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        when(service.updateDraft(anyString(), anyLong(), any(Ticket.class)))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tickets/{id}", String.valueOf(Long.MAX_VALUE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTicket1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).updateDraft(username, Long.MAX_VALUE, entityTicket1);
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_submitTicket() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        when(service.submitTicket(username, submitedEntityTicket1.getId()))
                .thenReturn(submitedEntityTicket1);
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tickets/{id}/submit", String.valueOf(submitedDtoTicket1.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTicket1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).submitTicket(username,submitedEntityTicket1.getId());
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_submitTicket_notFound() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        when(service.submitTicket(anyString(), anyLong()))
                .thenThrow(new EntityNotFoundException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tickets/{id}/submit", String.valueOf(Long.MAX_VALUE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTicket1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).submitTicket(username, Long.MAX_VALUE);
    }
  
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_submitTicket_badRequest() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        when(service.submitTicket(anyString(), anyLong()))
                .thenThrow(new IllegalArgumentException());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/tickets/{id}/submit", String.valueOf(dtoTicket1.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dtoTicket1)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        verify(service).submitTicket(username, dtoTicket1.id());
    }
    
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_delete() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        doNothing().when(service).delete(username, submitedEntityTicket1.getId());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/tickets/{id}", String.valueOf(submitedDtoTicket1.id())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        verify(service).delete(username,submitedEntityTicket1.getId());
    }
   
    @Test
    @WithMockUser(username = "test-user", roles = {"USER"})
    public void test_delete_notFound() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        doThrow(new EntityNotFoundException())
                .when(service).delete(anyString(), anyLong());
        
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/tickets/{id}", String.valueOf(Long.MAX_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        
        verify(service).delete(username, Long.MAX_VALUE);
    }
    
}

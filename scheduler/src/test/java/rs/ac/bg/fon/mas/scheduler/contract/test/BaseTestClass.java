package rs.ac.bg.fon.mas.scheduler.contract.test;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.scheduler.controller.MatchController;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@ActiveProfiles("test")
public abstract class BaseTestClass {
    
    @Autowired
    MatchController controller;
    
    @MockBean
    MatchService service;
    
    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(controller);
        
        League l = new League(1L, "", 1, "", "Premier League", "", 5, null);
        Team eve  = new Team(1L, "Everton", "", "", "", "");
        Team liv  = new Team(2L, "Liverpool", "", "", "", "");

        Team ars  = new Team(3L, "Arsenal", "", "", "", "");
        Team che  = new Team(4L, "Chelsea", "", "", "", "");
        
        Match m1 = new Match(1L, l, eve, liv, "Regualr season - 1", LocalDateTime.parse("2024-10-15T20:00:00"), MatchStatus.COMPLETED, 0, 0);
        Match m2 = new Match(2L, l, ars, che, "Regualr season - 1", LocalDateTime.parse("2024-10-15T20:00:00"), MatchStatus.COMPLETED, 2, 0);        
        
        Mockito.when(service.getByIds(List.of(1L, 2L)))
                .thenReturn(List.of(m1, m2));
    }
    
}

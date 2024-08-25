/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@ActiveProfiles("test")
public class TicketRepositoryTest {

    @Autowired
    TicketRepository repo;

    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        Prediction prediction1 = new Prediction(11L, PredictionOutcome.HOME_WIN, PredictionStatus.ACTIVE);
        Prediction prediction2 = new Prediction(12L, PredictionOutcome.AWAY_WIN, PredictionStatus.ACTIVE);
        Prediction prediction3 = new Prediction(13L, PredictionOutcome.DRAW, PredictionStatus.ACTIVE);
        ticket = new Ticket("euzebio90", LocalDateTime.now(), TicketStatus.ACTIVE, List.of(prediction1, prediction2, prediction3));
    }

    @Test
    public void testFindByStatus() {
        Ticket savedTicket = null;
        try {
            savedTicket = repo.save(ticket);

            Assertions.assertNotNull(savedTicket);
            Assertions.assertNotNull(savedTicket.getId());

            List<Ticket> tickets = repo.findByStatus(TicketStatus.ACTIVE);
            Assertions.assertEquals(1, tickets.size());
        } finally {
            if (savedTicket != null) {
                repo.delete(savedTicket);
            }
            
        }
    }

    @Test
    public void testFindByUsername() {
        Ticket savedTicket = null;
        try {
            savedTicket = repo.save(ticket);

            Assertions.assertNotNull(savedTicket);
            Assertions.assertNotNull(savedTicket.getId());

            List<Ticket> tickets = repo.findByUsername(ticket.getUsername());
            Assertions.assertEquals(1, tickets.size());
        } finally {
            if (savedTicket != null) {
                repo.delete(savedTicket);
            }
            
        }
    }

    @Test
    public void testFindByPredictionMatchId() {
        Ticket savedTicket = null;
        try {
            savedTicket = repo.save(ticket);

            Assertions.assertNotNull(savedTicket);
            Assertions.assertNotNull(savedTicket.getId());

            List<Ticket> tickets = repo.findByPredictionMatchIdAndStatus(
                    ticket.getPredictions().get(0).getMatchId(),
                    TicketStatus.ACTIVE
            );
            Assertions.assertEquals(1, tickets.size());
        } finally {
            if (savedTicket != null) {
                repo.delete(savedTicket);
            }
            
        }
    }    
    
}

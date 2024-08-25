/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.ticketing.client.SchedulerApiClient;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;
import rs.ac.bg.fon.mas.ticketing.dto.MatchDto;
import rs.ac.bg.fon.mas.ticketing.messaging.dto.enums.MatchOutcome;
import rs.ac.bg.fon.mas.ticketing.repository.TicketRepository;
import rs.ac.bg.fon.mas.ticketing.service.TicketService;

/**
 *
 * @author Predrag
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository repo;

    @Autowired
    SchedulerApiClient client;

    @Override
    public Ticket createEmtyTicket(String username) {
        Ticket ticket = new Ticket(username, LocalDateTime.now(), TicketStatus.DRAFT, null);
        return repo.save(ticket);
    }

    @Override
    public Ticket updateDraft(String username, Ticket ticket) {
        Ticket entity = repo.findById(ticket.getId()).orElseThrow(() -> new EntityNotFoundException());
        entity.setPredictions(ticket.getPredictions());
        return repo.save(entity);
    }

    @Override
    public Ticket submitTicket(String username, Long id) {
        Ticket entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException());

        if (!this.isValid(entity)) {
            throw new IllegalArgumentException();
        }

        entity.setStatus(TicketStatus.ACTIVE);
        return repo.save(entity);
    }

    @Override
    public boolean isValid(Ticket ticket) {
        List<Long> matchIds = ticket.getPredictions().stream()
                .map(Prediction::getMatchId)
                .collect(Collectors.toList());

        List<MatchDto> matches = client.getMatchesByIds(matchIds);
        for (MatchDto match : matches) {
            if (!match.status().equalsIgnoreCase("Scheduled")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Ticket updateStatus(String username, Ticket ticket) {
        Ticket entity = repo.findById(ticket.getId()).orElseThrow(() -> new EntityNotFoundException());
        entity.setStatus(ticket.getStatus());
        return repo.save(entity);
    }

    @Override
    public List<Ticket> getAll(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public Ticket getById(String username, Long id) {
        Ticket entity = repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return entity;
    }

    @Override
    public List<Ticket> getByStatus(String username, String status) {
        TicketStatus ticketStatus;
        try {
            ticketStatus = TicketStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        return repo.findByStatus(ticketStatus);
    }

    @Override
    public void delete(String username, Long id) {
        repo.deleteById(id);
    }

    @Override
    public void checkTickets(Long matchId) {
        List<Ticket> tickets = repo.findByPredictionMatchIdAndStatus(matchId, TicketStatus.ACTIVE);
        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.WIN);
            for (Prediction pred : ticket.getPredictions()) {
                if (pred.getStatus() == PredictionStatus.LOSE) {
                    ticket.setStatus(TicketStatus.LOSE);
                    break;
                } else if (pred.getStatus() == PredictionStatus.ACTIVE) {
                    ticket.setStatus(TicketStatus.ACTIVE);
                }
            }

        }
        repo.saveAll(tickets);
    }

}

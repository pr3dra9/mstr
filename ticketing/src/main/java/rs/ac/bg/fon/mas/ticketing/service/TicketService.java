/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.service;

import java.util.List;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;

/**
 *
 * @author Predrag
 */
public interface TicketService {
    Ticket createEmtyTicket(String username);
    Ticket updateDraft(String username, Long id, Ticket ticket);
    Ticket submitTicket(String username, Long id);
    Ticket updateStatus(String username, Long id, Ticket ticket);
    boolean isValid(Ticket ticket);
    
    List<Ticket> getAll(String username);
    Ticket getById(String username, Long id);
    List<Ticket> getByStatus(String username, String status);
    void delete(String username, Long id);
    
    void checkTickets(Long matchId);
    
}

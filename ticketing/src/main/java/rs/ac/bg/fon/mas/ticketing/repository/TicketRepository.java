/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;

/**
 *
 * @author Predrag
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    List<Ticket> findByUsername(String username);
    List<Ticket> findByStatus(TicketStatus status);
    
    @Query("SELECT t FROM Ticket t JOIN t.predictions p WHERE p.matchId = :matchId AND t.status = :status")
    List<Ticket> findByPredictionMatchIdAndStatus(@Param("matchId") Long matchId, @Param("status") TicketStatus status);

}

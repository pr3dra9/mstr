/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.bg.fon.mas.ticketing.dto.TicketDto;

/**
 *
 * @author Predrag
 */
@RequestMapping("/tickets")
public interface TicketingController {

    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<List<TicketDto>> getAll();

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id);

    @GetMapping(value = "/by-status/{status}",
            produces = "application/json")
    public ResponseEntity<List<TicketDto>> getByStatus(@PathVariable String status);

    @PostMapping(value = "",
            produces = "application/json")
    public ResponseEntity<TicketDto> createEmptyTicket();
    
    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto dto);
    
    @PutMapping(value = "/{id}/submit",
            produces = "application/json")
    public ResponseEntity<TicketDto> submitTicket(@PathVariable Long id);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Void> deleteById(@PathVariable Long id);

}

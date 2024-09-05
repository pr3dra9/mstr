/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Ticket API", 
        description = "Operations for managing tickets, including creation, updates, and retrieval by status or ID.")
public interface TicketingController {

    @GetMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Retrieve all tickets", 
            description = "Fetches a list of all tickets available in the system.")
    public ResponseEntity<List<TicketDto>> getAll();

    @GetMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Retrieve a ticket by ID", 
            description = "Fetches details of a specific ticket using its unique ID.")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id);

    @GetMapping(value = "/by-status/{status}",
            produces = "application/json")
    @Operation(summary = "Retrieve tickets by status", 
            description = "Fetches a list of tickets that match the specified status.")
    public ResponseEntity<List<TicketDto>> getByStatus(@PathVariable String status);

    @PostMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Create an empty ticket", 
            description = "Creates a new empty ticket in the system.")
    public ResponseEntity<TicketDto> createEmptyTicket();
    
    @PutMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Update an existing ticket", 
            description = "Updates the details of an existing ticket by its ID.")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto dto);
    
    @PutMapping(value = "/{id}/submit",
            produces = "application/json")
    @Operation(summary = "Submit a ticket", 
            description = "Submits the ticket for processing after all details are finalized.")
    public ResponseEntity<TicketDto> submitTicket(@PathVariable Long id);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Delete a ticket by ID", 
            description = "Deletes a specific ticket from the system by its ID.")
    public ResponseEntity<Void> deleteById(@PathVariable Long id);

}

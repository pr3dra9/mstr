/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.mas.ticketing.controller.TicketingController;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.dto.TicketDto;
import rs.ac.bg.fon.mas.ticketing.mapper.TicketMapper;
import rs.ac.bg.fon.mas.ticketing.service.TicketService;

/**
 *
 * @author Predrag
 */
@RestController
public class TicketingControllerImpl implements TicketingController{

    @Autowired
    TicketService service;
    
    @Autowired
    TicketMapper<TicketDto> mapper;
    
    @Override
    public ResponseEntity<List<TicketDto>> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Ticket> entityList = service.getAll(username);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entityList));
    }

    @Override
    public ResponseEntity<TicketDto> getById(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Ticket entity = service.getById(username, id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<List<TicketDto>> getByStatus(String status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Ticket> entityList = service.getByStatus(username, status);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entityList));
    }

    @Override
    public ResponseEntity<TicketDto> createEmptyTicket() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Ticket entity = service.createEmtyTicket(username);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<TicketDto> updateTicket(Long id, TicketDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Ticket entity = service.updateDraft(username, id,  mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<TicketDto> submitTicket(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Ticket entity = service.submitTicket(username, id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        service.delete(username, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}

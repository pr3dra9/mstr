/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.mas.scheduler.controller.AdminLeagueController;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.LeagueMapper;
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.service.LeagueService;

/**
 *
 * @author Predrag
 */
@RestController
public class AdminLeagueControllerImpl implements AdminLeagueController{

    @Autowired
    LeagueService service;
    
    @Autowired
    LeagueMapper<LeagueDto> mapper;
    
    @Override
    public ResponseEntity<Page<LeagueDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<League> entities = service.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entities));
    }

    @Override
    public ResponseEntity<LeagueDto> getById(Long id) {
        League entity = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<LeagueDto> create(LeagueDto dto) {
        League entity = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<LeagueDto> update(Long id, LeagueDto dto) {
        League entity = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<LeagueDto> addTeams(Long id, Set<Long> teamIds) {
        League entity = service.addTeams(id, teamIds);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<LeagueDto> removeTeams(Long id, Set<Long> teamIds) {
        League entity = service.removeTeams(id, teamIds);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<LeagueDto> replaceTeams(Long id, Set<Long> teamIds) {
        League entity = service.replaceTeams(id, teamIds);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }
    
}

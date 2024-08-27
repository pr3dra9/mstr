/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.mas.scheduler.controller.AdminTeamController;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.TeamMapper;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.service.TeamService;

/**
 *
 * @author Predrag
 */
@RestController
public class AdminTeamControllerImpl implements AdminTeamController{

    @Autowired
    TeamService service;
    
    @Autowired
    TeamMapper<TeamDto> mapper;
    
    @Override
    public ResponseEntity<Page<TeamDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Team> entityTeams = service.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entityTeams));
    }

    @Override
    public ResponseEntity<TeamDto> getById(Long id) {
        Team entity = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<TeamDto> create(TeamDto dto) {
        Team entity = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<TeamDto> update(Long id, TeamDto dto) {
        Team entity = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}

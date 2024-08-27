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
import rs.ac.bg.fon.mas.scheduler.controller.AdminMatchController;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.MatchMapper;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@RestController
public class AdminMatchControllerImpl implements AdminMatchController{

    @Autowired
    MatchService service;
    
    @Autowired
    MatchMapper<MatchDto> mapper;
    
    @Override
    public ResponseEntity<Page<MatchDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Match> entities = service.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entities));
    }

    @Override
    public ResponseEntity<MatchDto> getById(Long id) {
        Match entity = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<MatchDto> create(MatchDto dto) {
        Match entity = service.create(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<MatchDto> update(Long id, MatchDto dto) {
        Match entity = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(entity));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}

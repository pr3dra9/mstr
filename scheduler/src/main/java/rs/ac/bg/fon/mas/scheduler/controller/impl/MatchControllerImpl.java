/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.mas.scheduler.controller.MatchController;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.MatchMapper;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@RestController
public class MatchControllerImpl implements MatchController {

    @Autowired
    private MatchService service;
    
    @Autowired
    private MatchMapper<MatchDto> mapper;
    
    @Override
    public ResponseEntity<Page<MatchDto>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var entities = service.getAll(pageable);                    
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDtos(entities));
    }

    @Override
    public ResponseEntity<MatchDto> getById(Long id) {        
        Match m = service.getById(id);
        
        if (m == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MatchDto dtoMatch = mapper.toDto(m);
        return ResponseEntity.status(HttpStatus.OK).body(dtoMatch);
    }

    @Override
    public ResponseEntity<List<MatchDto>> getByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            
        List<Match> matches = service.getByIds(ids);
        
        List<MatchDto> dtoMatches = matches.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(dtoMatches);
    }

    @Override
    public ResponseEntity<List<MatchDto>> getAllForPeriod(String startDate, String endDate) {

        List<Match> matches 
                = service.getAllForPeriod(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        
        List<MatchDto> dtoMatches = matches.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(dtoMatches);
    }

}

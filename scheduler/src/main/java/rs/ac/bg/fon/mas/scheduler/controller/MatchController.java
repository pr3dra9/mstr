/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;

/**
 *
 * @author Predrag
 */

@RequestMapping("/matches")
public interface MatchController {
    
    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<List<MatchDto>> getAll();
    
    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<MatchDto> getById(@PathVariable Long id);
    
    @GetMapping(value = "/by-ids",
            produces = "application/json")
    public ResponseEntity<List<MatchDto>> getByIds(@RequestParam List<Long> ids);
    
    @GetMapping(value = "/by-period",
            produces = "application/json")
    public ResponseEntity<List<MatchDto>> getAllForPeriod(@RequestParam String startDate, @RequestParam String endDate);
    
}

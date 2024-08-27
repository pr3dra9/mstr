/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;

/**
 *
 * @author Predrag
 */
@RequestMapping("/admin/matches")
public interface AdminMatchController {
    
    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<Page<MatchDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<MatchDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    public ResponseEntity<MatchDto> create(@RequestBody MatchDto dto);

    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<MatchDto> update(@PathVariable Long id, @RequestBody MatchDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id);

}

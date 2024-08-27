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
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;

/**
 *
 * @author Predrag
 */
@RequestMapping("/admin/teams")
public interface AdminTeamController {

    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<Page<TeamDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<TeamDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    public ResponseEntity<TeamDto> create(@RequestBody TeamDto dto);

    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<TeamDto> update(@PathVariable Long id, @RequestBody TeamDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id);

}

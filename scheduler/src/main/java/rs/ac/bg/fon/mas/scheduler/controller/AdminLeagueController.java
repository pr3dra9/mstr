/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import java.util.Set;
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
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;

/**
 *
 * @author Predrag
 */
@RequestMapping("/admin/leagues")
public interface AdminLeagueController {

    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<Page<LeagueDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<LeagueDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    public ResponseEntity<LeagueDto> create(@RequestBody LeagueDto dto);
    
    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<LeagueDto> update(@PathVariable Long id, @RequestBody LeagueDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id);
    
    @PutMapping(value = "/{id}/add-teams",
            produces = "application/json")
    public ResponseEntity<LeagueDto> addTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);
    
    @PutMapping(value = "/{id}/remove-teams",
            produces = "application/json")
    public ResponseEntity<LeagueDto> removeTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);
    
    @PutMapping(value = "/{id}/replace-teams",
            produces = "application/json")
    public ResponseEntity<LeagueDto> replaceTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);

}

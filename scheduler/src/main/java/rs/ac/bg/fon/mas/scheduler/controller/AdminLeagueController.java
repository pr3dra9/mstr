/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="League API", 
        description = "Operations for managing Leagues and their associated Teams.")
public interface AdminLeagueController {

    @GetMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Retrieve all leagues", 
            description = "Fetches a paginated list of all leagues available in the system.")
    public ResponseEntity<Page<LeagueDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Retrieve a league by ID")
    public ResponseEntity<LeagueDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Create a new league")
    public ResponseEntity<LeagueDto> create(@RequestBody LeagueDto dto);
    
    @PutMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Update an existing league")
    public ResponseEntity<LeagueDto> update(@PathVariable Long id, @RequestBody LeagueDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Delete a league by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id);
    
    @PutMapping(value = "/{id}/add-teams",
            produces = "application/json")
    @Operation(summary = "Add teams to a league", 
            description = "Adds a set of teams to the specified league by their IDs. The teams must already exist in the system.")
    public ResponseEntity<LeagueDto> addTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);
    
    @PutMapping(value = "/{id}/remove-teams",
            produces = "application/json")
    @Operation(summary = "Remove teams from a league", 
            description = "Removes a set of teams from the specified league by their IDs.")
    public ResponseEntity<LeagueDto> removeTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);
    
    @PutMapping(value = "/{id}/replace-teams",
            produces = "application/json")
    @Operation(summary = "Replace teams in a league", 
            description = "Replaces all existing teams in the specified league with the provided set of team IDs.")
    public ResponseEntity<LeagueDto> replaceTeams(@PathVariable Long id, @RequestBody Set<Long> teamIds);

}

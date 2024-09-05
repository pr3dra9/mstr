/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Team API", 
        description = "Operations for managing teams, including creation, update, and deletion of teams.")
@RequestMapping("/admin/teams")
public interface AdminTeamController {

    @GetMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Retrieve all teams", 
            description = "Fetches a paginated list of all teams available in the system.")
    public ResponseEntity<Page<TeamDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Retrieve a team by ID", 
            description = "Fetches details of a specific team by its unique ID.")
    public ResponseEntity<TeamDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Create a new team", 
            description = "Creates a new team and stores it in the system.")
    public ResponseEntity<TeamDto> create(@RequestBody TeamDto dto);

    @PutMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Update an existing team", 
            description = "Updates the details of an existing team based on the provided team data.")
    public ResponseEntity<TeamDto> update(@PathVariable Long id, @RequestBody TeamDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Delete a team by ID", 
            description = "Deletes a specific team from the system by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id);

}

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
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;

/**
 *
 * @author Predrag
 */
@Tag(name = "Match API", 
        description = "Operations for managing matches, including creating, updating, and deleting matches.")
@RequestMapping("/admin/matches")
public interface AdminMatchController {
    
    @GetMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Retrieve all matches", 
            description = "Fetches a paginated list of all matches in the system.")
    public ResponseEntity<Page<MatchDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @GetMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Retrieve a match by ID", 
            description = "Fetches details of a specific match by its unique ID.")
    public ResponseEntity<MatchDto> getById(@PathVariable Long id);

    @PostMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Create a new match", 
            description = "Creates a new match and stores it in the system.")
    public ResponseEntity<MatchDto> create(@RequestBody MatchDto dto);

    @PutMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Update an existing match", 
            description = "Updates the details of an existing match using the provided match data.")
    public ResponseEntity<MatchDto> update(@PathVariable Long id, @RequestBody MatchDto dto);
    
    @DeleteMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Delete a match by ID", 
            description = "Deletes a specific match from the system by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id);

}

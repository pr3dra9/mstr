/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;

/**
 *
 * @author Predrag
 */

@Tag(name = "Match API", 
        description = "Operations for retrieving match data, including querying by ID, period, or multiple IDs.")
@RequestMapping("/matches")
public interface MatchController {
    
    @GetMapping(value = "",
            produces = "application/json")
    @Operation(summary = "Retrieve all matches", 
            description = "Fetches a paginated list of all matches available in the system.")
    public ResponseEntity<Page<MatchDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);
    
    @GetMapping(value = "/{id}",
            produces = "application/json")
    @Operation(summary = "Retrieve a match by ID", 
            description = "Fetches the details of a specific match using its unique ID.")
    public ResponseEntity<MatchDto> getById(@PathVariable Long id);
    
    @GetMapping(value = "/by-ids",
            produces = "application/json")
    @Operation(summary = "Retrieve matches by IDs", 
            description = "Fetches a list of matches based on the provided list of match IDs.")
    public ResponseEntity<List<MatchDto>> getByIds(@RequestParam List<Long> ids);
    
    @GetMapping(value = "/by-period",
            produces = "application/json")
    @Operation(summary = "Retrieve matches for a specific period", 
            description = "Fetches all matches that occurred within the specified start and end dates.")
    public ResponseEntity<List<MatchDto>> getAllForPeriod(@RequestParam String startDate, @RequestParam String endDate);
    
}

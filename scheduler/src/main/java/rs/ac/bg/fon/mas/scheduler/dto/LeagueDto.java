/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.dto;

import java.util.Set;

/**
 *
 * @author Predrag
 */
public record LeagueDto(
        Long id,
        String region,
        Integer rank,
        String season,
        String name,
        String logo,
        Integer rounds,
        Set<TeamDto> teams) {

}

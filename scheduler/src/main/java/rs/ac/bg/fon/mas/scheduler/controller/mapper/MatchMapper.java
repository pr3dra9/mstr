/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper;

import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.model.Match;

/**
 *
 * @author Predrag
 */
public interface MatchMapper {
    Match toEntity(MatchDto dto);
    MatchDto toDto(Match entity);    
}

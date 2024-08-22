/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.MatchMapper;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.model.Match;

/**
 *
 * @author Predrag
 */
@Component
public class MatchMapperImpl implements MatchMapper{

    @Override
    public Match toEntity(MatchDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MatchDto toDto(Match entity) {
        return new MatchDto(entity.getLeague().getName(),
                entity.getHomeTeam().getName(),
                entity.getAwayTeam().getName(),
                entity.getRound().toString(),
                entity.getDate().toString(),
                entity.getStatus().getDisplayName()
        );
    }
    
}

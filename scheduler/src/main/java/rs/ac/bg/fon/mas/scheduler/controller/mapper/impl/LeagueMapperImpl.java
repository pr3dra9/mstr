/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.LeagueMapper;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.TeamMapper;
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
@Component
public class LeagueMapperImpl implements LeagueMapper<LeagueDto> {
    
    @Autowired
    private TeamMapper<TeamDto> teamMapper;

    @Override
    public League toEntity(LeagueDto dto) {
        if (dto == null) {
            return null;
        }
        return new League(
            dto.id(),
            dto.region(),
            dto.rank(),
            dto.season(),
            dto.name(),
            dto.logo(),
            dto.rounds(),
            toTeamEntities(dto.teams())
        );
    }

    @Override
    public List<League> toEntitties(List<LeagueDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                   .map(this::toEntity)
                   .collect(Collectors.toList());
    }

    @Override
    public LeagueDto toDto(League entity) {
        if (entity == null) {
            return null;
        }
        return new LeagueDto(
            entity.getId(),
            entity.getRegion(),
            entity.getRank(),
            entity.getSeason(),
            entity.getName(),
            entity.getLogo(),
            entity.getRounds(),
            toTeamDtos(entity.getTeams())
        );
    }

    @Override
    public List<LeagueDto> toDtos(List<League> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                       .map(this::toDto)
                       .collect(Collectors.toList());
    }

    @Override
    public Page<LeagueDto> toDtos(Page<League> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map((entity) -> toDto(entity));
        
    }

    private Set<Team> toTeamEntities(Set<TeamDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                   .map(teamMapper::toEntity)
                   .collect(Collectors.toSet());
    }

    private Set<TeamDto> toTeamDtos(Set<Team> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                       .map(teamMapper::toDto)
                       .collect(Collectors.toSet());
    }
}


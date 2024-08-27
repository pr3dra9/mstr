/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.TeamMapper;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
@Component
public class TeamMapperImpl implements TeamMapper<TeamDto> {

    @Override
    public Team toEntity(TeamDto dto) {
        if (dto == null) {
            return null;
        }
        return new Team(
            dto.id(),
            dto.name(),
            dto.logo(),
            dto.country(),
            dto.city(),
            dto.stadium()
        );
    }

    @Override
    public List<Team> toEntitties(List<TeamDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                   .map(this::toEntity)
                   .collect(Collectors.toList());
    }

    @Override
    public TeamDto toDto(Team entity) {
        if (entity == null) {
            return null;
        }
        return new TeamDto(
            entity.getId(),
            entity.getName(),
            entity.getLogo(),
            entity.getCountry(),
            entity.getCity(),
            entity.getStadium()
        );
    }

    @Override
    public List<TeamDto> toDtos(List<Team> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                       .map(this::toDto)
                       .collect(Collectors.toList());
    }

    @Override
    public Page<TeamDto> toDtos(Page<Team> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map(this::toDto);
    }
    
}


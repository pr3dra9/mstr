/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.LeagueMapper;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.MatchMapper;
import rs.ac.bg.fon.mas.scheduler.controller.mapper.TeamMapper;
import rs.ac.bg.fon.mas.scheduler.dto.LeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchLeagueDto;
import rs.ac.bg.fon.mas.scheduler.dto.MatchTeamDto;
import rs.ac.bg.fon.mas.scheduler.dto.TeamDto;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;

/**
 *
 * @author Predrag
 */
@Component
public class MatchMapperImpl implements MatchMapper<MatchDto> {
    
    @Override
    public Match toEntity(MatchDto dto) {
        if (dto == null) {
            return null;
        }
        return new Match(
                dto.id(),
                new League(dto.league().id(), "", 0, "", dto.league().name(), "", 0, null),
                new Team(dto.homeTeam().id(), dto.homeTeam().name(), "", "", "", ""),
                new Team(dto.awayTeam().id(), dto.awayTeam().name(), "", "", "", ""),
                dto.round(),
                LocalDateTime.parse(dto.date()),
                MatchStatus.valueOf(dto.status().toUpperCase()),
                dto.homeTeamGoals(),
                dto.awayTeamGoals()
        );
    }

    @Override
    public List<Match> toEntities(List<MatchDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDto toDto(Match entity) {
        if (entity == null) {
            return null;
        }
        return new MatchDto(
                entity.getId(),
                new MatchLeagueDto(entity.getLeague().getId(), entity.getLeague().getName()),
                new MatchTeamDto(entity.getHomeTeam().getId(), entity.getHomeTeam().getName()),
                new MatchTeamDto(entity.getAwayTeam().getId(), entity.getAwayTeam().getName()),
                entity.getRound(),
                entity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                entity.getStatus().toString(),
                entity.getHomeTeamGoals(),
                entity.getAwayTeamGoals()
        );
    }

    @Override
    public List<MatchDto> toDtos(List<Match> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MatchDto> toDtos(Page<Match> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map((entity) -> toDto(entity));
    }

}
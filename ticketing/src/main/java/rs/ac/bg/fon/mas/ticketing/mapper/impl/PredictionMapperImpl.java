/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionStatus;
import rs.ac.bg.fon.mas.ticketing.dto.PredictionDto;
import rs.ac.bg.fon.mas.ticketing.mapper.PredictionMapper;

/**
 *
 * @author Predrag
 */
@Component
public class PredictionMapperImpl implements PredictionMapper<PredictionDto> {

    @Override
    public PredictionDto toDto(Prediction entity) {
        if (entity == null) {
            return null;
        }

        return new PredictionDto(
                entity.getId(),
                entity.getMatchId(),
                entity.getOutcome().name(),
                entity.getStatus().name()
        );
    }

    @Override
    public Prediction toEntity(PredictionDto dto) {
        if (dto == null) {
            return null;
        }

        return new Prediction(
                dto.id(),
                dto.matchId(),
                PredictionOutcome.valueOf(dto.outcome()),
                PredictionStatus.valueOf(dto.status())
        );
    }

    @Override
    public List<PredictionDto> toDtos(List<Prediction> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prediction> toEntities(List<PredictionDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    
}


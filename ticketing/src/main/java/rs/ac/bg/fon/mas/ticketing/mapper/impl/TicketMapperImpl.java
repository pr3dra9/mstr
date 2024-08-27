/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.mapper.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;
import rs.ac.bg.fon.mas.ticketing.dto.TicketDto;
import rs.ac.bg.fon.mas.ticketing.mapper.PredictionMapper;
import rs.ac.bg.fon.mas.ticketing.mapper.TicketMapper;

/**
 *
 * @author Predrag
 */
@Component
public class TicketMapperImpl implements TicketMapper<TicketDto> {

    @Autowired
    private PredictionMapper predictionMapper;

    @Override
    public TicketDto toDto(Ticket entity) {
        if (entity == null) {
            return null;
        }

        return new TicketDto(
                entity.getId(),
                entity.getUsername(),
                entity.getDate().toString(),
                entity.getStatus().name(),
                predictionMapper.toDtos(entity.getPredictions())
        );
    }

    @Override
    public Ticket toEntity(TicketDto dto) {
        if (dto == null) {
            return null;
        }

        return new Ticket(
                dto.id(),
                dto.username(),
                LocalDateTime.parse(dto.date()),
                TicketStatus.valueOf(dto.status()),
                predictionMapper.toEntities(dto.predictions())
        );
    }

    @Override
    public List<TicketDto> toDtos(List<Ticket> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> toEntities(List<TicketDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

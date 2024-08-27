/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper;

import java.util.List;
import org.springframework.data.domain.Page;
import rs.ac.bg.fon.mas.scheduler.model.League;

/**
 *
 * @author Predrag
 */
public interface LeagueMapper<T> {
    League toEntity(T dto);
    List<League> toEntitties(List<T> dtos);
    
    T toDto(League entity);
    List<T> toDtos(List<League> entitties);
    Page<T> toDtos(Page<League> entitties);

}

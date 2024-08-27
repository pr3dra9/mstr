/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper;

import java.util.List;
import org.springframework.data.domain.Page;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
public interface TeamMapper<T> {
    Team toEntity(T dto);
    List<Team> toEntitties(List<T> dtos);
    
    T toDto(Team entity);
    List<T> toDtos(List<Team> entitties);
    Page<T> toDtos(Page<Team> entitties);

}

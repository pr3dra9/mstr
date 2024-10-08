/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.controller.mapper;

import java.util.List;
import org.springframework.data.domain.Page;
import rs.ac.bg.fon.mas.scheduler.model.Match;

/**
 *
 * @author Predrag
 */
public interface MatchMapper<T> {
    Match toEntity(T dto);
    List<Match> toEntities(List<T> dtos);
    
    
    T toDto(Match entity);
    List<T> toDtos(List<Match> entities);
    Page<T> toDtos(Page<Match> entities);
    
}

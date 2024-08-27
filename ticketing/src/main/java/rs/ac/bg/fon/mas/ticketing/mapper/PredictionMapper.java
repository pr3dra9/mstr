/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.mapper;

import java.util.List;
import rs.ac.bg.fon.mas.ticketing.domain.Prediction;

/**
 *
 * @author Predrag
 */
public interface PredictionMapper<T> {
    T toDto(Prediction entity);
    Prediction toEntity(T dto);
    
    List<T> toDtos(List<Prediction> entities);
    List<Prediction> toEntities(List<T> dtos);
}

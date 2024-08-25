/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.mapper;

import java.util.List;
import rs.ac.bg.fon.mas.ticketing.domain.Ticket;

/**
 *
 * @author Predrag
 */
public interface TicketMapper<T> {
    T toDto(Ticket entity);
    Ticket toEntity(T dto);
    
    List<T> toDtos(List<Ticket> entities);
    List<Ticket> toEntities(List<T> dtos);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.dto;

import java.util.List;

/**
 *
 * @author Predrag
 */
public record TicketDto (Long id, String username, String date, String status,List<PredictionDto> predictions) { }

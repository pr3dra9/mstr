/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.dto;

/**
 *
 * @author Predrag
 */
public record TeamDto(
        Long id,
        String name,
        String logo,
        String country,
        String city,
        String stadium) {

}

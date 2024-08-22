/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
public interface TeamService {
    Team save(Team team);
    Team update(Team team);
    boolean deleteById(Long id);
    boolean delete(Team team);
}

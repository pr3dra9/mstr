/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
public interface TeamService {
    Team create(Team team);
    Page<Team> getAll(Pageable pageable);
    Team getById(Long id);
    Team update(Long id, Team team);
    boolean deleteById(Long id);
}

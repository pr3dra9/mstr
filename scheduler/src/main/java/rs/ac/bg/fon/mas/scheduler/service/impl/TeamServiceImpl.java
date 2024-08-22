/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.repository.TeamRepository;
import rs.ac.bg.fon.mas.scheduler.service.TeamService;

/**
 *
 * @author Predrag
 */
@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepo;

    public TeamServiceImpl(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }
    
    @Override
    public Team save(Team team) {
        Team searchedTeam = teamRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity())
                .orElse(null);
        if (searchedTeam != null) {
            throw new IllegalStateException("Team with name " + team.getName() + " in " + team.getCity()+ ", " + team.getCountry() + " already exists.");
        }
        Team savedTeam = teamRepo.save(team);
        return savedTeam;
    }

    @Override
    public Team update(Team team) {
        teamRepo.findById(team.getId())
            .orElseThrow(() -> new EntityNotFoundException("Entity with given ID does not exist!"));
        
        Team savedTeam = teamRepo.save(team);
        
        return savedTeam;
    }

    @Override
    public boolean deleteById(Long id) {
        teamRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with given ID does not exist!"));
        
        teamRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean delete(Team team) {
        Team entityTeam = teamRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity())
            .orElseThrow(() -> new EntityNotFoundException("Entity with given name, country and city does not exist!"));
        
        teamRepo.deleteById(entityTeam.getId());
        return true;
    }
    
}

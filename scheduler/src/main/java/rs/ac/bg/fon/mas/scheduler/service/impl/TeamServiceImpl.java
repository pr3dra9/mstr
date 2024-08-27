/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Team> getAll(Pageable pageable) {
        return teamRepo.findAll(pageable);
    }

    @Override
    public Team getById(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException());
    }
    
    @Override
    public Team create(Team team) {
        Team searchedTeam = teamRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity())
                .orElse(null);
        if (searchedTeam != null) {
            throw new EntityExistsException("Team with name " + team.getName() + " in " + team.getCity()+ ", " + team.getCountry() + " already exists.");
        }
        Team savedTeam = teamRepo.save(team);
        return savedTeam;
    }

    @Override
    public Team update(Long id, Team team) {
        teamRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with given ID does not exist!"));
        team.setId(id);
        
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
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.scheduler.model.Team;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class TeamRepositoryTest {
    
    @Autowired
    TeamRepository teamRepo;
    
    @Test
    public void testTeamCreate() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team savedTeam = null;
        try {
            savedTeam = teamRepo.save(team);
            
            assertNotNull(savedTeam.getId());
            
            assertEquals("Arsenal", savedTeam.getName(), "Name should be Arsenal");
            assertEquals("ars.png", savedTeam.getLogo(), "Logo should be ars.png");
            assertEquals("England", savedTeam.getCountry(), "Country should be England");
            assertEquals("London", savedTeam.getCity(), "City should be London");
            assertEquals("Etihad", savedTeam.getStadium(), "Stadium should be Etihad");
            
        } finally {
            if (savedTeam != null) {
                teamRepo.delete(savedTeam);
            }
        }
    }

    @Test
    public void testTeamRead() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team savedTeam = null;
        try {
            savedTeam = teamRepo.save(team);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());
            
            savedTeam = teamRepo.findById(savedTeam.getId()).orElse(null);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());            
            assertEquals("Arsenal", savedTeam.getName(), "Name should be Arsenal");
            assertEquals("ars.png", savedTeam.getLogo(), "Logo should be ars.png");
            assertEquals("England", savedTeam.getCountry(), "Country should be England");
            assertEquals("London", savedTeam.getCity(), "City should be London");
            assertEquals("Etihad", savedTeam.getStadium(), "Stadium should be Etihad");
        } finally {
            if (savedTeam != null) {
                teamRepo.delete(savedTeam);
            }
        }    
    }

    @Test
    public void testFindByNameAndCountryAndCity() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team savedTeam = null;
        try {
            savedTeam = teamRepo.save(team);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());
            
            savedTeam = teamRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity())
                    .orElse(null);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());            
            assertEquals("Arsenal", savedTeam.getName(), "Name should be Arsenal");
            assertEquals("ars.png", savedTeam.getLogo(), "Logo should be ars.png");
            assertEquals("England", savedTeam.getCountry(), "Country should be England");
            assertEquals("London", savedTeam.getCity(), "City should be London");
            assertEquals("Etihad", savedTeam.getStadium(), "Stadium should be Etihad");
        } finally {
            if (savedTeam != null) {
                teamRepo.delete(savedTeam);
            }
        }    
    }
    
    @Test
    public void testTeamUpdate() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team savedTeam = null;
        try {
            savedTeam = teamRepo.save(team);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());
            
            savedTeam.setCity("London 1");
            savedTeam.setCountry("England 1");
            savedTeam.setLogo("ars1.png");
            savedTeam.setName("Arsenal 1");
            savedTeam.setStadium("Etihad 1");
            
            savedTeam = teamRepo.save(savedTeam);
            
            assertNotNull(savedTeam.getId());            
            assertEquals("Arsenal 1", savedTeam.getName(), "Name should be Arsenal 1");
            assertEquals("ars1.png", savedTeam.getLogo(), "Logo should be ars1.png");
            assertEquals("England 1", savedTeam.getCountry(), "Country should be England 1");
            assertEquals("London 1", savedTeam.getCity(), "City should be London 1");
            assertEquals("Etihad 1", savedTeam.getStadium(), "Stadium should be Etihad 1");        
        } finally {
            if (savedTeam != null) {
                teamRepo.delete(savedTeam);
            }
        }    
    }

    @Test
    public void testTeamDelete() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team savedTeam = null;
        try {
            savedTeam = teamRepo.save(team);
            
            assertNotNull(savedTeam);
            assertNotNull(savedTeam.getId());
            
            teamRepo.delete(savedTeam);
            
            savedTeam = teamRepo.findById(savedTeam.getId()).orElse(null);
            
            assertNull(savedTeam);
        } finally {
            if (savedTeam != null) {
                teamRepo.delete(savedTeam);
            }
        }    
    }
    
}

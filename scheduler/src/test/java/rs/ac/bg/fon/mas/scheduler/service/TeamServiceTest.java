/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.repository.TeamRepository;
import rs.ac.bg.fon.mas.scheduler.service.impl.TeamServiceImpl;

/**
 *
 * @author Predrag
 */
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
public class TeamServiceTest {
    
    @Mock
    private TeamRepository mockRepo;
    
    @InjectMocks
    private TeamServiceImpl service;

    @Test
    public void testSave() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team entiryTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        
        when(mockRepo.save(team)).thenReturn(entiryTeam);
        when(mockRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity()))
                .thenReturn(Optional.empty());

        
        Team savedTeam = service.create(team);

        assertNotNull(savedTeam);
        assertNotNull(savedTeam.getId());
        
        assertEquals("Arsenal", savedTeam.getName(), "Name should be Arsenal");
        assertEquals("ars.png", savedTeam.getLogo(), "Logo should be ars.png");
        assertEquals("England", savedTeam.getCountry(), "Country should be England");
        assertEquals("London", savedTeam.getCity(), "City should be London");
        assertEquals("Etihad", savedTeam.getStadium(), "Stadium should be Etihad");
        
        verify(mockRepo).findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity());
        verify(mockRepo).save(team);
        
    }

    @Test
    public void testSave_throwsException_entityExist() {
        Team team = new Team("Arsenal", "ars.png", "England", "London", "Etihad");
        Team entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        
        when(mockRepo.findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity()))
                .thenReturn(Optional.of(entityTeam));
        
        assertThrows(EntityExistsException.class, () -> {
            service.create(team);
        });
        
        verify(mockRepo).findByNameAndCountryAndCity(team.getName(), team.getCountry(), team.getCity());
        verify(mockRepo, times(0)).save(team);
        
    }
    
    @Test
    public void testUpdate() {
        Team entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
        Team editedTeam1 = new Team("Arsenal_edited", "ars_edited.png", "England_edited", "London_edited", "Etihad_edited");
        Team editedTeam2 = new Team(1L, "Arsenal_edited", "ars_edited.png", "England_edited", "London_edited", "Etihad_edited");
        
        when(mockRepo.findById(anyLong())).thenReturn(Optional.of(entityTeam));
        when(mockRepo.save(editedTeam2)).thenReturn(editedTeam2);
        
        Team savedTeam = service.update(entityTeam.getId(), editedTeam1);
        
        assertNotNull(savedTeam);
        assertNotNull(savedTeam.getId());
        
        assertEquals("Arsenal_edited", savedTeam.getName(), "Name should be Arsenal_edited");
        assertEquals("ars_edited.png", savedTeam.getLogo(), "Logo should be ars_edited.png");
        assertEquals("England_edited", savedTeam.getCountry(), "Country should be England_edited");
        assertEquals("London_edited", savedTeam.getCity(), "City should be London_edited");
        assertEquals("Etihad_edited", savedTeam.getStadium(), "Stadium should be Etihad_edited");
        
        verify(mockRepo).findById(entityTeam.getId());
        verify(mockRepo).save(editedTeam2);
    }

    @Test
    public void testUpdate_throwsException_entityDoesntExist() {
        Team editedTeam = new Team(1L, "Arsenal_edited", "ars_edited.png", "England_edited", "London_edited", "Etihad_edited");
        
        when(mockRepo.findById(anyLong())).thenReturn(Optional.empty());
                
        assertThrows(EntityNotFoundException.class, () -> {
            service.update(editedTeam.getId(), editedTeam);
        });
        
        verify(mockRepo).findById(anyLong());
        verify(mockRepo, times(0)).save(editedTeam);
    }
    
    @Test
    public void testDeleteById() {
       Team entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
       when(mockRepo.findById(anyLong())).thenReturn(Optional.of(entityTeam));
       doNothing().when(mockRepo).deleteById(anyLong());
       
       boolean signal = service.deleteById(1L);
       
       assertTrue(signal);
       
       verify(mockRepo).findById(anyLong());
       verify(mockRepo).deleteById(anyLong());
       
    }

    @Test
    public void testDeleteById_throwsException_entityDoesntExist() {
       when(mockRepo.findById(anyLong())).thenReturn(Optional.empty());
       
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteById(1L);
        });
       
       verify(mockRepo).findById(anyLong());
    }

    @Test
    public void testDelete() {
       Team entityTeam = new Team(1L, "Arsenal", "ars.png", "England", "London", "Etihad");
       
       when(mockRepo.findById(anyLong()))
               .thenReturn(Optional.of(entityTeam));
       
       doNothing().when(mockRepo).deleteById(anyLong());
       
       boolean signal = service.deleteById(1L);
       
       assertTrue(signal);
       
       verify(mockRepo).findById(anyLong());
       verify(mockRepo).deleteById(anyLong());
       
    }

}

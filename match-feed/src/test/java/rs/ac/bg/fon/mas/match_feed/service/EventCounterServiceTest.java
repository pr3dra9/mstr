/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;
import rs.ac.bg.fon.mas.match_feed.repository.EventCounterRepository;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@ActiveProfiles("test")
public class EventCounterServiceTest {
    
    @MockBean
    EventCounterRepository repo;
    
    @Autowired
    EventCounterService service;
    
    @Test
    public void testGetCounter() {
        //EventCounter eventCounter = new EventCounter(123L, 5);
        EventCounter entity = new EventCounter(1L, 123L, 5);
        Mockito.when(repo.findByUuid(123L)).thenReturn(Optional.of(entity));
        
        int counter = service.getCounter(123L);
        
        Assertions.assertEquals(5, counter);
        Mockito.verify(repo).findByUuid(123L);        
    }

    @Test
    public void testGetCounter_noEntity() {
        //EventCounter eventCounter = new EventCounter(123L, 5);
        //EventCounter entity = new EventCounter(1L, 123L, 5);
        Mockito.when(repo.findByUuid(123L)).thenReturn(Optional.empty());
        
        int counter = service.getCounter(123L);
        
        Assertions.assertEquals(0, counter);
        Mockito.verify(repo).findByUuid(123L);        
    }
    
    
    @Test
    public void testSaveEventCounter() {
        EventCounter entity = new EventCounter(1L, 123L, 5);
        EventCounter entityEdited = new EventCounter(1L, 123L, 8);
        
        Mockito.when(repo.findByUuid(123L)).thenReturn(Optional.of(entity));
        Mockito.when(repo.save(entityEdited)).thenReturn(entityEdited);
        
        var db = service.saveEventCounter(entityEdited);
        
        Assertions.assertEquals(8, db.getCounter());
        
        
        Mockito.verify(repo).findByUuid(123L);
        Mockito.verify(repo).save(entityEdited);        
    
    }

    @Test
    public void testSaveEventCounter_noEntity() {
        EventCounter entity = new EventCounter(1L, 123L, 8);
        
        Mockito.when(repo.findByUuid(123L)).thenReturn(Optional.empty());
        Mockito.when(repo.save(entity)).thenReturn(entity);
        
        var db = service.saveEventCounter(entity);
        
        Assertions.assertEquals(8, db.getCounter());
        
        
        Mockito.verify(repo).findByUuid(123L);
        Mockito.verify(repo).save(entity);        
    
    }
    
}

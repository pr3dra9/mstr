/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service.impl;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;
import rs.ac.bg.fon.mas.match_feed.repository.EventCounterRepository;

/**
 *
 * @author Predrag
 */
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false"
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventCounterServiceImplTest {
    
    @Mock
    EventCounterRepository repo;
    
    @InjectMocks
    EventCounterServiceImpl service;
    
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
    
    @Test
    public void test_isFinished_false() {
        EventCounter counter = new EventCounter(1L, 2323L, 2, false);
        
        Mockito.when(repo.findByUuid(1L))
                .thenReturn(Optional.of(counter));
        
        boolean finished = service.isFinished(1L);
        
        
        Assertions.assertEquals(false, finished);
        
        verify(repo).findByUuid(1L);
    }

    @Test
    public void test_isFinished_true() {
        EventCounter counter = new EventCounter(1L, 2323L, 2, true);
        
        Mockito.when(repo.findByUuid(1L))
                .thenReturn(Optional.of(counter));
        
        boolean finished = service.isFinished(1L);
        
        
        Assertions.assertEquals(true, finished);
        
        verify(repo).findByUuid(1L);
    }

    @Test
    public void test_isFinished_entityDoesNotExists() {
        Mockito.when(repo.findByUuid(anyLong()))
                .thenReturn(Optional.empty());
        
        boolean finished = service.isFinished(2323L);
        
        
        Assertions.assertEquals(false, finished);
        
        verify(repo).findByUuid(2323L);
    }
    
    @Test
    public void test_setFinished_entityExists() {
        Long uuid = 2323L;
        EventCounter entityFalse = new EventCounter(1L, uuid, 2, false);
        EventCounter entityTrue = new EventCounter(1L, uuid, 2, true);
        
        Mockito.when(repo.findByUuid(uuid))
            .thenReturn(Optional.of(entityFalse));
        
        Mockito.when(repo.save(entityTrue))
            .thenReturn(entityTrue);
        
        boolean finished = service.setFinished(uuid);
        
        Assertions.assertTrue(finished);
        
        verify(repo).findByUuid(uuid);
        verify(repo).save(entityTrue);
    }

    @Test
    public void test_setFinished_entityDoesNotExists() {
        Long uuid = 2323L;
        EventCounter objectTrue = new EventCounter(uuid, 0, true);
        EventCounter entityTrue = new EventCounter(1L, uuid, 0, true);
        
        Mockito.when(repo.findByUuid(uuid))
            .thenReturn(Optional.empty());
        
        Mockito.when(repo.save(objectTrue))
            .thenReturn(entityTrue);
        
        boolean finished = service.setFinished(uuid);
        
        Assertions.assertTrue(finished);
        
        verify(repo).findByUuid(uuid);
        verify(repo).save(objectTrue);
    }

}

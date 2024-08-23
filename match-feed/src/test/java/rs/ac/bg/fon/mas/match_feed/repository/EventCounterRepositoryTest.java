/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@ActiveProfiles("test")
public class EventCounterRepositoryTest {
    
    @Autowired
    EventCounterRepository repo;
    
    @Test
    public void testFindByUuid() {
        EventCounter counter = new EventCounter(12345L, 5);
        
        repo.save(counter);
        
        EventCounter entity = repo.findByUuid(12345L).orElse(null);
        
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(12345L, entity.getUuid());
        assertEquals(5, entity.getCounter());

        repo.delete(entity);
        
    }
    
    @Test
    public void testFindByUuid_noEntity() {
        EventCounter entity = repo.findByUuid(12345L).orElse(null);
        
        assertNull(entity);        
    }
    
    @Test
    public void save() {
        EventCounter counter = new EventCounter(12345L, 5);
        
        EventCounter entity = repo.save(counter);
        
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(12345L, entity.getUuid());
        assertEquals(5, entity.getCounter());

        repo.delete(entity);
        
    }
    
}

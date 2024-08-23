/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 *
 * @author Predrag
 */
@Entity
public class EventCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uuid;
    private int counter; 

    public EventCounter() {
    }

    public EventCounter(Long uuid, int counter) {
        this.uuid = uuid;
        this.counter = counter;
    }
    
    public EventCounter(Long id, Long uuid, int counter) {
        this.id = id;
        this.uuid = uuid;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "EventCounter{" + "id=" + id + ", uuid=" + uuid + ", counter=" + counter + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.uuid);
        hash = 67 * hash + this.counter;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventCounter other = (EventCounter) obj;
        if (this.counter != other.counter) {
            return false;
        }
        return Objects.equals(this.uuid, other.uuid);
    }
    
    

}

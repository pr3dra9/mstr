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
    private boolean finished;

    public EventCounter() {
    }

    public EventCounter(Long uuid, int counter) {
        this.uuid = uuid;
        this.counter = counter;
        this.finished = false;
    }

    public EventCounter(Long uuid, int counter, boolean finished) {
        this.uuid = uuid;
        this.counter = counter;
        this.finished = finished;
    }
    
    public EventCounter(Long id, Long uuid, int counter) {
        this.id = id;
        this.uuid = uuid;
        this.counter = counter;
        this.finished = false;
    }

    public EventCounter(Long id, Long uuid, int counter, boolean finished) {
        this.id = id;
        this.uuid = uuid;
        this.counter = counter;
        this.finished = finished;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }    
    
    @Override
    public String toString() {
        return "EventCounter{" + "id=" + id + ", uuid=" + uuid + ", counter=" + counter + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.uuid);
        hash = 61 * hash + this.counter;
        hash = 61 * hash + (this.finished ? 1 : 0);
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
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.uuid, other.uuid);
    }
    
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import rs.ac.bg.fon.mas.ticketing.domain.enums.TicketStatus;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private LocalDateTime date;
    private TicketStatus status;

    @OneToMany(orphanRemoval = true, 
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.REMOVE
            },
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_fk", referencedColumnName = "id")
    private List<Prediction> predictions;

    public Ticket() {
    }

    public Ticket(String username, LocalDateTime date, TicketStatus status, List<Prediction> predictions) {
        this.username = username;
        this.date = date;
        this.status = status;
        this.predictions = predictions;
    }
    
    public Ticket(Long id, String username, LocalDateTime date, TicketStatus status, List<Prediction> predictions) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.status = status;
        this.predictions = predictions;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.predictions);
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
        final Ticket other = (Ticket) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.predictions.size() != other.predictions.size()) {
            return false;
        }
        
        var sottedLiasA = this.predictions.stream().collect(Collectors.toList());
        var sottedLiasB = other.predictions.stream().collect(Collectors.toList());
        
        sottedLiasA.sort((p1, p2) -> Long.compare(p1.getId(), p2.getId()));
        sottedLiasB.sort((p1, p2) -> Long.compare(p1.getId(), p2.getId()));
        
        for (int i = 0; i < sottedLiasA.size(); i++) {
            if (!sottedLiasA.get(i).equals(sottedLiasB.get(i))) {
                return false;
            }
        }
        return true;
    }

}

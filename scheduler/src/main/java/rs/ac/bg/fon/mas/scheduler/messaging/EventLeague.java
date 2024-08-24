/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging;

/**
 *
 * @author Predrag
 */
public class EventLeague {
    private String name;
    private String country;
    private String round;

    public EventLeague(String name, String country, String round) {
        this.name = name;
        this.country = country;
        this.round = round;
    }

    public EventLeague() {
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    
    
    @Override
    public String toString() {
        return "EventLeague{" + "name=" + name + ", country=" + country + ", round=" + round + '}';
    }
    
}

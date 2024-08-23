/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventLeague {
    public final String name;
    public final String country;
    public final String round;

    public EventLeague(String name, String country, String round) {
        this.name = name;
        this.country = country;
        this.round = round;
    }

    @Override
    public String toString() {
        return "EventLeague{" + "name=" + name + ", country=" + country + ", round=" + round + '}';
    }
    
}

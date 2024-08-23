/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventTeams {
    public final String home;
    public final String away;

    public EventTeams(String home, String away) {
        this.home = home;
        this.away = away;
    }

    @Override
    public String toString() {
        return "EventTeams{" + "home=" + home + ", away=" + away + '}';
    }
    
}

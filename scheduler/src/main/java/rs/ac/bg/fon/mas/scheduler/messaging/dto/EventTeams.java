/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.dto;

/**
 *
 * @author Predrag
 */
public class EventTeams {
    private String home;
    private String away;

    public EventTeams(String home, String away) {
        this.home = home;
        this.away = away;
    }

    public EventTeams() {
    }

    @Override
    public String toString() {
        return "EventTeams{" + "home=" + home + ", away=" + away + '}';
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }
    
}

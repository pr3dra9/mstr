/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging;

/**
 *
 * @author Predrag
 */
public class EventGoals {
    private int home;
    private int away;

    public EventGoals(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public EventGoals() {
    }

    
    @Override
    public String toString() {
        return "EventGoals{" + "home=" + home + ", away=" + away + '}';
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }
    
}

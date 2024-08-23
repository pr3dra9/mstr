/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventGoals {
    public final int home;
    public final int away;

    public EventGoals(int home, int away) {
        this.home = home;
        this.away = away;
    }

    @Override
    public String toString() {
        return "EventGoals{" + "home=" + home + ", away=" + away + '}';
    }
    
}

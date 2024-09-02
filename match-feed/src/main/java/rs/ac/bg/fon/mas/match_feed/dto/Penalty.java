/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class Penalty{
    public Object home;
    public Object away;

    public Penalty() {
    }

    public Penalty(Object home, Object away) {
        this.home = home;
        this.away = away;
    }
    
    @Override
    public String toString() {
        return "Penalty{" + "home=" + home + ", away=" + away + '}';
    }
}
